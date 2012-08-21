package api

import cz.urbo.web.api.utils.org.apache.commons.httpclient.HttpStatus
import groovy.json.JsonBuilder
import cz.urbo.cases.Author
import cz.urbo.cases.Feedback
import cz.urbo.cases.Location
import cz.urbo.cases.Photo
import java.awt.image.BufferedImage

import static org.imgscalr.Scalr.*
import javax.imageio.ImageIO
import grails.validation.ValidationException;

/**
 * This is WEB API of URBO Application
 */
class ApiFeedbackController {

    static allowedMethods = [save: 'POST']

    def feedbackService

    def index() {
        findAll()
    }

    def findAll() {
        def feedbacks = Feedback.list()
        def feedbacksAsJson = feedbackService.convertToJson(feedbacks)
        render(contentType: "application/json", text: feedbacksAsJson)
    }

    /**
     * This action is used to save feedback through JSON post request.
     */
    def save() {

        def feedbackParams = request.JSON.feedback // when parseRequest in urlmapping is true then params.feedback is ok
        try {
            Author authorObject = findOrCreateAuthor(feedbackParams)
            Feedback feedback = createFeedback(feedbackParams, authorObject)
            feedback.save()
            def json = new JsonBuilder(
                    status: HttpStatus.SC_OK,
                    message: "Feedback has been successfully saved - id of saved feedback is accessible " +
                            "in 'feedback_id' property of this response.",
                    feedback_id: feedback.id)

            render (
                    status: HttpStatus.SC_OK,
                    contentType: "application/json",
                    text: json.toPrettyString(),
                    encoding: "utf-8"
            )
        } catch (ValidationException e) {

            def allErrorsAsText =
                e.errors.allErrors.collect {
                    "Property '${it.field}' cannot be '${it.rejectedValue}'"
                }.join("\n")

            def json = new JsonBuilder(
                    "status": HttpStatus.SC_BAD_REQUEST, /* 400 - Bad Request -
                                                  this should be the same as http response code we use,
                                                  because developer  can work directly with json response
                                                  and don't have to use http status codes to detect errors..
                                                  or he can */
                    "message": allErrorsAsText)

            render (
                    contentType: "application/json",
                    text: json.toPrettyString(),
                    status: HttpStatus.SC_BAD_REQUEST, /* HttpCode for Bad Request */
                    encoding: "utf-8")
        }
    }

    private Feedback createFeedback(feedbackParams, Author authorObject) {
        def feedback = new Feedback(
                title: feedbackParams.title,
                description: feedbackParams.description,
                location: new Location(latitude: feedbackParams.latitude,
                        longitude: feedbackParams.longitude),
                author: authorObject,
                photo: Photo.findById(feedbackParams.photo_id))
        feedback
    }

    private Author findOrCreateAuthor(feedbackParams) {
        def author = [
                identification: feedbackParams.identification,
                provider: feedbackParams.provider,
                name:  feedbackParams.name]

        def authorObject = Author.findByProviderAndIdentification(author.provider, author.identification)

        if (!authorObject) {
            //author not found - let's create one
            authorObject = new Author(identification: author.identification, provider: author.provider, name: author.name)
            //and save it to database
            authorObject.save()
        }
    }

    def findById() {
        def feedbackParam = params.id
        def feedback = Feedback.findById(feedbackParam)

        if (feedback == null) {

           //TODO michal : tbd reaction of api error (ie. nothing found)

        }
        else {
            def feedbacksAsJson = feedbackService.convertToJson(feedback)
            render(contentType: "application/json", text: feedbacksAsJson)
        }

    }

    def uploadPhoto() {

        def uploadedFile = request.getFile("file")
        def photo = new Photo()
        photo.data = uploadedFile.getBytes()

        photo.save()

        // TODO mbernhard : exception reaction

        def jsonBuilder = new JsonBuilder(
                                status: HttpStatus.SC_OK,
                                message:
                                        "File uploaded successfully and saved with id ${photo.id}. " +
                                        "Use this id when creating new feedback through api as photoId parameter." +
                                        "PhotoId is easily accessible as photoId parameter of this response object.",
                                photoId: photo.id)

        def jsonResponse = jsonBuilder.toPrettyString()

        render(status: HttpStatus.SC_OK, contentType: "application/json", text: jsonResponse)

    }

    def getPhoto() {

        def photoId = params.id
        def photo = Photo.findById(photoId)
        assert photo != null

//        render(photo.data)

        if (photo) {
            response.contentType = "image/jpeg"
            response.addHeader("Content-disposition", "filename='photo${photo.id}.jpeg'")
            response.outputStream << photo.data
            return
        }
        else {
            render("Photo with id ${photoId} is not available")
        }

    }

    def getPhotoThumbnail() {

        def photoId = params.id
        def photo = Photo.findById(photoId)
        assert photo != null

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(photo.data))
        BufferedImage thumbnail =
            resize(image, Method.SPEED, Mode.FIT_TO_WIDTH,
                    200, 200, OP_ANTIALIAS);

        response.contentType = "image/jpeg"
        response.addHeader("Content-disposition", "filename='photo${photo.id}.jpeg'")
        ImageIO.write(thumbnail, "JPEG", response.outputStream)
        return
    }

}

package admin

import cz.urbo.cases.Photo
import grails.plugins.springsecurity.Secured

import static org.imgscalr.Scalr.*
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

@Secured(['ROLE_ADMIN'])
class PhotoController {
    static def scaffold = Photo
    def thumbnailCacheService


    public rotateLeft() {
        rotatePhotoAndRedirect(params.id, Rotation.CW_270)
    }

    public rotateRight() {
        rotatePhotoAndRedirect(params.id, Rotation.CW_90)
    }

    private rotatePhotoAndRedirect(id, Rotation rotation) {
        def photo = Photo.findById(id)
        assert photo != null

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(photo.data))
        image = rotate(image, rotation, OP_ANTIALIAS)
        photo.data = encodeImage(image)
        photo.save()

        //don't forget to invalidate thumbnail cache. Should invalidate only the specific thumbnail
        thumbnailCacheService.invalidate()

        redirect(action: "show", id: id)
    }




    private byte[] encodeImage(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", os)
        os.toByteArray()
    }
}

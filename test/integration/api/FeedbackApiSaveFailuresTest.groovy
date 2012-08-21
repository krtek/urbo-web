package api

import org.apache.commons.lang.StringUtils
import org.junit.Test
import org.springframework.http.HttpMethod
import cz.urbo.cases.Feedback
import api.ApiFeedbackController
import grails.test.mixin.TestFor
import cz.urbo.cases.Author


class FeedbackApiSaveFailuresTest extends GroovyTestCase {

    @Test
    void shouldNotSaveWhenSaveJsonApiWithMissingTitleIsCalled() {
        def controller = new ApiFeedbackController()

        controller.request.contentType = "text/json"
        controller.request.content = '''

            {
                "feedback": {
                    "description": "podrobnější popis problému",
                    "latitude": 50.076,
                    "longitude": 14.408,
                    "photo_id": "",
                    "provider" : "GOOGLE",
                    "identification" : "budulinek@liska.cz",
                    "name" : "Budulínek"
                }
            }

        '''.getBytes("utf-8")

        controller.request.requestMethod = HttpMethod.POST

        controller.save()

        assert Feedback.list().size() == 0 : "title attribute is required so without it it shouldn't be saved at all"
    }

    @Test
    void shouldResponseWithErrorJsonWhenSaveJsonApiWithMissingTitleIsCalled() {
        def controller = new ApiFeedbackController()
        controller.request.contentType = "text/json"
        controller.request.content = '''

            {
                "feedback": {
                    "description": "podrobnější popis problému",
                    "latitude": 50.076,
                    "longitude": 14.408,
                    "photo_id": "",
                    "provider" : "GOOGLE",
                    "identification" : "budulinek@liska.cz",
                    "name" : "Budulínek"
                }
            }

        '''.getBytes("utf-8")

        controller.request.requestMethod = HttpMethod.POST

        controller.save()

        def expectedJson = '{"status":400,"message":"Property \'title\' cannot be \'null\'"}'

        def responseContentAsString = controller.response.text
        assert StringUtils.deleteWhitespace(responseContentAsString) == StringUtils.deleteWhitespace(expectedJson)

    }


    @Test
    void shouldNotSaveWhenSaveJsonApiWithMissingAuthor() {
        def controller = new ApiFeedbackController()

        controller.request.contentType = "text/json"
        controller.request.content = '''

            {
                "feedback": {
                    "title" : "Titulek",
                    "description": "podrobnější popis problému",
                    "latitude": 50.076,
                    "longitude": 14.408,
                    "photo_id": "",
                    "provider" : "GOOGLE",
                    "name" : "Budulínek"
                }
            }

        '''.getBytes("utf-8")

        controller.request.requestMethod = HttpMethod.POST

        controller.save()

        assert Author.list().size() == 0 : "Author is required parameter"

        def expectedResponse = '{"status":400,"message":"Property \'identification\' cannot be \'null\'"}'
        def responseContentAsString = controller.response.text
        assert StringUtils.deleteWhitespace(responseContentAsString) == StringUtils.deleteWhitespace(expectedResponse) : "Error message should be exact."

    }


}

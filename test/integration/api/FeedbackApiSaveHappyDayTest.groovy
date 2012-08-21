package api

import org.junit.Before
import org.junit.Test
import org.springframework.http.HttpMethod
import cz.urbo.cases.Feedback
import api.ApiFeedbackController
import grails.test.mixin.TestFor
import cz.urbo.cases.Author

class FeedbackApiSaveHappyDayTest extends GroovyTestCase {

    @Before
    public void beforeEachTest() {

        def controller = new ApiFeedbackController()
        controller.request.contentType = "text/json"
        controller.request.content = '''

            {
                "feedback": {
                    "title": "přechod pro chodce má zelenou moc krátce",
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
        controller.request.content = '''
            {

        "feedback": {
            "title": "přechod pro chodce má zelenou moc krátce II",
            "description": "Už zase!",
            "latitude": 50.076,
            "longitude": 14.408,
            "photo_id": "",
            "provider" : "GOOGLE",
            "identification" : "budulinek@liska.cz"
            "name" : "Budulínek"
            }
        }
        '''.getBytes("utf-8")

        controller.save()
    }

    @Test
    public void callToJsonSaveApiShouldSaveAndTitleIsRight() {
        def feedback = Feedback.list().get(0)
        assert feedback.title == "přechod pro chodce má zelenou moc krátce"
    }

    @Test
    public void callToJsonSaveApiShouldSaveAndLatitudeIsRight() {
        def feedback = Feedback.list().get(0)
        assert feedback.location.latitude == 50.076
    }

    @Test
    public void callToJsonSaveApiShouldSaveAndLongitudeIsRight() {
        def feedback = Feedback.list().get(0)
        assert feedback.location.longitude == 14.408
    }

    @Test
    public void callToJsonSaveApiShouldSaveAndDescriptionIsRight() {
        def feedback = Feedback.list().get(0)
        assert  feedback.description == "podrobnější popis problému"
    }

    @Test
    public void callToJsonSaveApiShouldSaveAuthorOnlyOnce() {
        def authors = Author.list()
        assertEquals("Author should be saved only once!",  1, authors.size())
    }

    @Test
    public void callToJsonSaveApiShouldSaveAuthorRight() {
        def author = Author.list().get(0)
        assertEquals("Author id", "budulinek@liska.cz", author.identification)
        assertEquals("Author provider", "GOOGLE", author.provider)
    }

}
package api

import cz.superobcan.web.FeedbackTestUtils
import grails.test.MockUtils
import grails.test.mixin.*
import org.apache.commons.lang.StringUtils
import api.ApiFeedbackController
import cz.urbo.cases.Feedback
import cz.urbo.cases.Author
import cz.urbo.cases.Photo
import cz.urbo.cases.Location
import cz.urbo.cases.AuthorityResponse
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ApiFeedbackController)
@Mock([Author, AuthorityResponse, Feedback, Location, Photo])
class ApiFeedbackControllerUnitTests {

    void testFindAllJsonResponse() {

        /*
            When using TestFor only a subset of the Spring beans available to a running Grails application are
            available. If you wish to make additional beans available you can do so with the defineBeans method of
            GrailsUnitTestMixin (@TestFor do it for you)
         */
        defineBeans {
             feedbackService(web.FeedbackService)
        }

        /* otherwise Feedback.list() called in controller findAll throws NPE as Feedback is not initialized at all */
        MockUtils.mockDomain(Feedback, FeedbackTestUtils.createTestingFeedbacks())

        /* controller instance is injected by @TestFor annotation automatically */
        controller.findAll()

        def expectedJsonResponse = """
                                        {
                                            "feedbacks": [
                                                {
                                                    "id": 1,
                                                    "title": "přechod pro chodce má zelenou moc krátce",
                                                    "description": null,
                                                    "latitude": 50.076,
                                                    "longitude": 14.408,
                                                    "authority_response": null
                                                },
                                                {
                                                    "id": 2,
                                                    "title": "někdo hodil karamelovýho draka do pisoáru",
                                                    "description": "děsně to zapáchá",
                                                    "latitude": 50.07647,
                                                    "longitude": 14.40216,
                                                    "authority_response": null
                                                },
                                                {
                                                    "id": 3,
                                                    "title": "rozsypaná popelnice",
                                                    "description": null,
                                                    "latitude": 50.02678,
                                                    "longitude": 14.43455,
                                                    "authority_response": null
                                                }
                                            ]
                                        }

                                    """

        String.metaClass.deleteWhitespace = StringUtils.&deleteWhitespace

        assert response.text.deleteWhitespace() == expectedJsonResponse.deleteWhitespace()

    }

    void testFindAllByAuthorJsonResponse() {
        defineBeans {
             feedbackService(web.FeedbackService)
        }

        /* otherwise Feedback.list() called in controller findAll throws NPE as Feedback is not initialized at all */
        FeedbackTestUtils.createTestingFeedbacks()*.save()

        assertEquals(2, controller.findAllByProviderAndIdentification("GOOGLE", "michal@bernhard.cz", 0).size())
        assertEquals(0, controller.findAllByProviderAndIdentification("SEZNAM", "nikdo@seznam.cz", 0).size())
    }
}

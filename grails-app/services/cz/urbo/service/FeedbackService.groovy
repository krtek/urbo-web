package cz.urbo.service

import groovy.json.JsonBuilder
import cz.urbo.cases.Feedback

class FeedbackService {

    def convertToJson(Feedback feedback) {
        return convertToJson([feedback])
    }

    def convertToJson(List<Feedback> feedbacks) {

        def feedbacksConverted = feedbacks.collect { Feedback feedback ->
            [id: feedback.id,
             title: feedback.title,
             description: feedback.description,
             latitude: feedback.location?.latitude,
             longitude: feedback.location?.longitude,
             authority_response: feedback.authorityResponse?.content,
             state: feedback.state?.description,
             lastUpdated: feedback.lastUpdated?.format("dd.MM.yyyy"),
             dateCreated: feedback.dateCreated?.format("dd.MM.yyyy"),
             photoId: feedback.photo?.id]
        }

        def builder = new JsonBuilder(feedbacks: feedbacksConverted)

        return builder.toPrettyString()
    }
}

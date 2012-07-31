package urbo

import cz.superobcan.web.FeedbackState
import cz.urbo.cases.Feedback

class MapController {

    def index() {

        [lastFeedbacks: Feedback.findAllByStateNot(FeedbackState.CREATED, [max: 5, sort: "lastUpdated", order: "desc"]),
         allFeedbacks: Feedback.findAllByStateNot(FeedbackState.CREATED)]
    }

    def detail() {
        [feedback: Feedback.findById(params.id)]
    }
}

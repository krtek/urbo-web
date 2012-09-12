package cz.urbo.web

import cz.urbo.dto.FeedbackState
import cz.urbo.cases.Feedback

class MapController {

    def index() {
        if (!params.max) {
            params.max = 4;
        }

        if (!params.offset) {
            params.offset=0;
        }

        [lastFeedbacks: Feedback.findAllByStateNot(FeedbackState.CREATED, [max: params.max, sort: "lastUpdated", order: "desc", offset: params.offset]),
         allFeedbacks: Feedback.findAllByStateNot(FeedbackState.CREATED),
        count: Feedback.findAllByStateNot(FeedbackState.CREATED).size()]
    }

    def detail() {
        [feedback: Feedback.findById(params.id)]
    }
}

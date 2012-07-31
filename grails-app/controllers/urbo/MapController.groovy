package urbo

import cz.urbo.cases.Feedback

class MapController {

    def index() {
        [lastFeedbacks: Feedback.list(max: 5, sort: "lastUpdated", order: "desc"),
         allFeedbacks: Feedback.list()]
    }

    def detail() {
        [feedback: Feedback.findById(params.id)]
    }
}

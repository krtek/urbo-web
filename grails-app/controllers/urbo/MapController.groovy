package urbo

import cz.urbo.cases.Feedback

class MapController {

    def index() {
        [feedbacks: Feedback.list()]
    }

    def detail() {
        [feedback: Feedback.findById(params.id)]
    }
}

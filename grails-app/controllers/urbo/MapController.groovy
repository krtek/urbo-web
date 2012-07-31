package urbo

import cz.urbo.cases.Feedback

class MapController {
    List feedbacks
    Feedback feedback

    def index() {
        feedbacks = Feedback.list()
    }

    def detail() {
        feedback = Feedback.findById(params.id)
    }
}

package urbo

import cz.urbo.cases.Feedback

class MapController {
    List feedbacks

    def index() {
        feedbacks = Feedback.list()
    }
}

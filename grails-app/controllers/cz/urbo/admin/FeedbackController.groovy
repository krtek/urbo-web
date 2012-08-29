package cz.urbo.admin

import cz.urbo.cases.Feedback
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class FeedbackController {

    static def scaffold = Feedback

    def kontaktyNaUradyApiService

    def sendUrboItemToMunicipalAuthority() {
        def feedback = Feedback.findById(params.id)

        def feedbackLocationAddress = feedback.location.toAddress()
        def municipalAuthority = kontaktyNaUradyApiService.obtainMunicipalityGovernmentAgencyFor(feedbackLocationAddress)

        render(
            view: "/feedback/sendToMunicipalAuthority",
            model: [feedbackInstance: feedback, municipalAuthorityMail: municipalAuthority.email])
    }
}

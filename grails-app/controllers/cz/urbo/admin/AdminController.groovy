package cz.urbo.admin

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class AdminController {

    def index() {
        render(view: "/index")
    }
}

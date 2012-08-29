package cz.urbo.admin

import cz.urbo.cases.Author
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class AuthorController {

    static def scaffold = Author
}

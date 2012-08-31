package cz.urbo.cases

import cz.urbo.dto.FeedbackState
import groovy.transform.EqualsAndHashCode

/**
 *
 * Feedback is created by author and in the end it's send to government/city/district representative.
 *
 */
@EqualsAndHashCode
public class Feedback {

    //static embedded = ['photo', 'location', 'authorityResponse']

    static constraints = {
        title()
        description(nullable: true)
        location()
        author()
        photo(nullable: true)
        authorityResponse(nullable: true)
        sentToMunicipalAuthorityEmail(nullable: true)
        customMunicipalAuthorityEmailText(nullable: true)
        state()
        dateCreated()
        lastUpdated()
    }

    static mapping = {
        description type: "text"
    }

    String title
    Photo photo
    String description
    Location location
    Author author
    Date dateCreated
    Date lastUpdated

    /**
     * This is email address where urbo item is sent (or will be sent).
     */
    String sentToMunicipalAuthorityEmail

    /**
     * When we want to change the default text of email we're sending to municipal authority (in czech "zastupitelstvo")
     * we save new content here.
     */
    String customMunicipalAuthorityEmailText

    AuthorityResponse authorityResponse

    FeedbackState state = FeedbackState.CREATED // as this is default state

    @Override
    String toString() {
        "${author}: ${title}"
    }

}

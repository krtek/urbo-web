package cz.superobcan.web

import cz.urbo.cases.Author
import cz.urbo.cases.AuthorityResponse
import cz.urbo.cases.Feedback
import cz.urbo.cases.Location
import cz.urbo.cases.Photo

class FeedbackTestUtils {

    static def createTestingFeedbacks() {

        def photoOfUrboTheGreat = new Photo()
        def urboPictureStream = FeedbackTestUtils.class.getResourceAsStream("urbo_picture_for_dev_purposes.jpg")
        photoOfUrboTheGreat.data = urboPictureStream.getBytes()

        def michal = new Author(
                provider: "GOOGLE",
                identification: "michal@bernhard.cz",
                name: "Michal Bernhard",
                id: 1)

        def krtek =  new Author(
                provider: "GOOGLE",
                identification: "lukas.marek@gmail.com",
                name: "Lukáš Marek",
                id: 2)
        def anonymous = new Author(
                provider: "None",
                identification: "anonymous",
                name: "Anonymní zbabělec",
                id: 3)

        def authorityResponseText =
"""Milý občane,

Vámi nahlášený karamelový drak nebo chcete-li hadrová panenka, byl odstraněn dne
31.07.2012. Zápach jak věříme zmizí v nejbližších pár dnech v závislosti na tom jak a jestli bude
místnost větrána.

Děkujeme za zpětnou vazbu a těšíme se na Váš další podnět.

S pozdravem Marie Urbová
Odbor pro styk s veřejností, Městský úřad Praha 1
"""

        def authorityResponse = new AuthorityResponse(content: authorityResponseText)
        [
                michal,
                krtek,
                anonymous,
                photoOfUrboTheGreat,
                authorityResponse,

                new Feedback(
                        author: michal,
                        title: "Přechod pro chodce má zelenou moc krátce",
                        location: new Location(latitude: 50.076, longitude: 14.408),
                        state: FeedbackState.SENT_TO_AUTHORITY,
                        photo: photoOfUrboTheGreat
                ),

                new Feedback(
                        author: michal,
                        title: "Někdo hodil karamelovýho draka do pisoáru",
                        description: "děsně to zapáchá",
                        location: new Location(latitude: 50.07647, longitude: 14.40216),
                        state: FeedbackState.FIXED,
                        authorityResponse: authorityResponse
                ),

                new Feedback(
                        author: krtek,
                        title: "Rozsypaná popelnice",
                        description: "Na chodníku se válí krabice, slepice, konvice, truhlice, \n hned pošlete oranžový čepice!",
                        location: new Location(latitude: 50.02678, longitude: 14.43455),
                        state: FeedbackState.CREATED
                ),

                new Feedback(
                        author: anonymous,
                        title: "Nečitelná cedule",
                        description: "Rozsypaný text, nevalné podsvícení, prosím opravte to!",
                        location: new Location(latitude: 50.026, longitude: 14.30),
                        state: FeedbackState.CREATED
                )

        ]

    }
}

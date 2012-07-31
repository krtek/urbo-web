package cz.superobcan.web

import cz.urbo.cases.Author
import cz.urbo.cases.Email
import cz.urbo.cases.Feedback
import cz.urbo.cases.Location
import cz.urbo.cases.Photo

class FeedbackTestUtils {

    static def createTestingFeedbacks() {

        def photoOfUrboTheGreat = new Photo()
        def urboPictureStream = FeedbackTestUtils.class.getResourceAsStream("urbo_picture_for_dev_purposes.jpg")
        photoOfUrboTheGreat.data = urboPictureStream.getBytes()

        def michal = new Author(
                name: "Michal",
                surname: "Bernhard",
                email: new Email(address: "michal@bernhard.cz"))

        def krtek =  new Author(
                name: "Lukáš",
                surname: "Marek",
                email: new Email(address: "lukas.marek@gmail.com"))


        [
                michal,
                krtek,
                photoOfUrboTheGreat,

                new Feedback(
                        author: michal,
                        title: "Přechod pro chodce má zelenou moc krátce",
                        location: new Location(latitude: 50.076, longitude: 14.408),
                        state: FeedbackState.CREATED,
                        photo: photoOfUrboTheGreat
                ),

                new Feedback(
                        author: michal,
                        title: "Někdo hodil karamelovýho draka do pisoáru",
                        description: "děsně to zapáchá",
                        location: new Location(latitude: 50.07647, longitude: 14.40216),
                        state: FeedbackState.CREATED
                ),

                new Feedback(
                        author: krtek,
                        title: "Rozsypaná popelnice",
                        description: "Na chodníku se válí krabice, slepice, konvice, truhlice, \n hned pošlete oranžový čepice!",
                        location: new Location(latitude: 50.02678, longitude: 14.43455),
                        state: FeedbackState.CREATED
                )
        ]

    }
}

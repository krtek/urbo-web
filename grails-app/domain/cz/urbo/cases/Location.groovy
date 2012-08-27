package cz.urbo.cases

class Location {

    Double latitude
    Double longitude

    static constraints = {
        latitude(scale: 5)
        longitude(scale: 5)
    }

    static belongsTo = [feedback: Feedback]

    @Override
    String toString() {
        "${latitude}, ${longitude}"
    }
}

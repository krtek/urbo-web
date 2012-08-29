package cz.urbo.cases

import cz.urbo.dto.Address

class Location {

    def googleGeoCodeApiService

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

    Address toAddress() {
        googleGeoCodeApiService.transformLocationToAddress(this)
    }
}

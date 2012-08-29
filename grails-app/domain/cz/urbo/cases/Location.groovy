package cz.urbo.cases

import cz.urbo.dto.Address

class Location {

    def googleGeoCodeApiService

    Double latitude
    Double longitude

    static belongsTo = [feedback: Feedback]

    @Override
    String toString() {
        "${latitude}, ${longitude}"
    }

    Address toAddress() {
        googleGeoCodeApiService.transformLocationToAddress(this)
    }
}

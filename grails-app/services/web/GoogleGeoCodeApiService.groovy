package web

import cz.urbo.dto.Address
import cz.urbo.cases.Location
import groovy.json.JsonSlurper

/**
 * Wrapper for https://developers.google.com/maps/documentation/geocoding/
 */
class GoogleGeoCodeApiService {

    public Address transformLocationToAddress(Location location) {

        def latitude = location.latitude
        def longitude = location.longitude

        def googleGeoCodeApiRequestUrl = "http://maps.googleapis.com/maps/api/geocode/json?latlng=${latitude},${longitude}&sensor=false"

        def rawApiResponse = new URL(googleGeoCodeApiRequestUrl).text

        def jsonSlurper = new JsonSlurper();
        def jsonResponse = jsonSlurper.parseText(rawApiResponse)

        def addressComponents = jsonResponse.results[0].address_components
        def town = addressComponents.grep { it.types.contains("postal_town") }[0].long_name
        def streetName = addressComponents.grep { it.types.contains("route") }[0].long_name
        def streetNumber = addressComponents.grep { it.types.contains("street_number") }[0].long_name

        return new Address(town: town, streetName: streetName, streetNumber: streetNumber)

    }

}

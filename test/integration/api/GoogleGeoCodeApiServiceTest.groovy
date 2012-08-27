package api

import cz.urbo.cases.Location
import grails.test.mixin.TestFor
import org.junit.Test
import web.GoogleGeoCodeApiService

@TestFor(GoogleGeoCodeApiService)
class GoogleGeoCodeApiServiceTest extends GroovyTestCase {

    @Test
    public void townShouldBeResolved() {

        /* this is not needed as service variable is available thanks to @TestFor ast transformation */
        // def service = new GoogleGeoCodeApiService()

        def address = service.transformLocationToAddress(new Location(latitude: 50.076, longitude: 14.408))
        assertEquals("Praha 5", address.town)

    }

    @Test
    public void streetNameShouldBeResolved() {
        def address = service.transformLocationToAddress(new Location(latitude: 50.076, longitude: 14.408))
        assertEquals("Kořenského", address.streetName)
    }

    @Test
    public void streetNumberShouldBeResolved() {
        def address = service.transformLocationToAddress(new Location(latitude: 50.076, longitude: 14.408))
        assertEquals("3", address.streetNumber)
    }

}

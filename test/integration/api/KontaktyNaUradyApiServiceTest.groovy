package api

import cz.urbo.dto.Address
import grails.test.mixin.TestFor
import org.junit.Test
import cz.urbo.service.KontaktyNaUradyApiService

@TestFor(KontaktyNaUradyApiService)
class KontaktyNaUradyApiServiceTest extends GroovyTestCase {

    @Test
    public void shouldRespondWithRightAnswerForPrague() {

        def address = new Address(town: "Praha 4", streetName: "Nad Lesním divadlem", streetNumber: " 4a")
        def municipalAuthority = service.obtainMunicipalityGovernmentAgencyFor(address)

        assertEquals("posta@praha4.cz", municipalAuthority.email)

    }



}

package web

import cz.superobcan.web.Address
import cz.superobcan.web.MunicipalAuthority
import groovy.json.JsonSlurper

/**
 *  Wrapper to communicate with kontaktynaurady.cz api. Through this api we obtain
 *  mail contact to proper government agency (which is responsible to solve issues in the
 *  municipality) based on address.
 */
class KontaktyNaUradyApiService {

    private static final KONTAKTY_NA_URADY_API_URL_TEXT = "http://www.kontaktynaurady.cz/api/v1/"

    def obtainMunicipalityGovernmentAgencyFor(Address address)  {

        def requestUrl = KONTAKTY_NA_URADY_API_URL_TEXT + "organizations?name=*" + URLEncoder.encode(address.town, "ISO-8859-2") + "*"

        def slurper = new JsonSlurper()
        def json = slurper.parseText(new URL(requestUrl).text)

        return new MunicipalAuthority(
                    email: json.email,
                    dataBoxId: json.dataBoxId,
                    address: new Address(
                                    town: json.addressCity,
                                    streetName: json.addressStreet
                    ))

    }


}

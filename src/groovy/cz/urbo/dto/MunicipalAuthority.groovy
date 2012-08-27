package cz.urbo.dto

import cz.urbo.dto.Address

/**
 * Czech: mestsky urad
 */
class MunicipalAuthority {

    String email
    String dataBoxId; // in czech: "id datove schranky", see http://www.datoveschranky.info/

    Address address;

}

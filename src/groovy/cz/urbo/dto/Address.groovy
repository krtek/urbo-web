package cz.urbo.dto

class Address {

    def town
    def streetNumber
    def streetName

    @Override
    String toString() {
        "${streetName} ${streetNumber}, ${town}"
    }

}

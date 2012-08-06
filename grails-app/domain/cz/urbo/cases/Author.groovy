package cz.urbo.cases

class Author {

    static constraints = {
        provider()
        identification()
    }

    /** Author provider service. E.g. Google, Facebook, Twitter  **/
    String provider;
    /** Author ID in provider scope. This would be probably an e-mail address. */
    String identification;



    @Override
    String toString() {
        //hide e-mail – if present
        (identification ==~ /.*@.*/) ? identification.replaceFirst(/@.*/, "@...") : identification
    }
}

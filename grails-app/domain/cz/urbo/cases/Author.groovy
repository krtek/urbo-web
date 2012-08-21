package cz.urbo.cases

class Author {

    static constraints = {
        name()
        provider()
        identification()
    }

    /** Author provider service. E.g. Google, Facebook, Twitter  **/
    String provider;
    /** Author ID in provider scope. This would be probably an e-mail address. */
    String identification;
    /** Human readable name. E.g. Alfons Mucha.*/
    String name;



    @Override
    String toString() {
        name
    }
}

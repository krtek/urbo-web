package cz.urbo.user

class Role {

	String authority

	static mapping = {
		cache true
        table 'urbo_role'
	}

	static constraints = {
		authority blank: false, unique: true
	}

    @Override
    String toString() {
        "${authority}"
    }
}

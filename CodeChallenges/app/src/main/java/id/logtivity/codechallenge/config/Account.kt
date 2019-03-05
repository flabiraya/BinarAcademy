package id.logtivity.codechallenge.config

data class Account (var name: String?, var email: String?, var organization: String?, var password: String?) {
    constructor() : this(null, null, null, null)
}
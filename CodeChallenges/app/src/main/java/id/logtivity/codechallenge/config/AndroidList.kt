package id.logtivity.codechallenge.config

data class AndroidList(var codeName: String?, var version: String?, var apiLevel: String?) {
    constructor() : this(null, null, null)
}
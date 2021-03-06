package id.flabiraya.movieddbbinarcodechallange.data.source.remote.error

import com.google.gson.annotations.SerializedName

class ErrorResponse {

    @SerializedName("error")
    private val mError: Error? = null

    val error: Error
        get() = mError ?: Error()

    class Error {
        @SerializedName("code")
        val code: Int = 0
        @SerializedName("description")
        private val messages: List<String>? = null
        val message: String? = null
    }
}

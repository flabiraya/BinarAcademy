package id.flabiraya.movieddbbinarcodechallange.data.source.remote.response

import com.google.gson.annotations.SerializedName
import id.flabiraya.movieddbbinarcodechallange.data.model.Movie

data class PopularResponse(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("total_results") val total_results: Int? = null,
    @SerializedName("total_pages") val total_pages: Int? = null,
    @SerializedName("results") val movies: List<Movie>? = null
)
package id.flabiraya.movieddbbinarcodechallange.data.source.remote.response

import com.google.gson.annotations.SerializedName
import id.flabiraya.movieddbbinarcodechallange.data.model.Genre

data class GenresReponse(

    @SerializedName("genres") val genres: List<Genre>
)
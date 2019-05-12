package id.flabiraya.movieddbbinarcodechallange.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.flabiraya.movieddbbinarcodechallange.R
import id.flabiraya.movieddbbinarcodechallange.data.model.Genre
import id.flabiraya.movieddbbinarcodechallange.data.model.Movie
import id.flabiraya.movieddbbinarcodechallange.databinding.ItemHomeBinding

class HomeAdapter(val onItemClick: (movie: Movie) -> Unit) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var _movies = mutableListOf<Movie>()

    private var _genres = mutableListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val homeAdapterBinding = DataBindingUtil.inflate<ItemHomeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home,
            parent,
            false
        )
        return ViewHolder(homeAdapterBinding, _genres, onItemClick)
    }

    override fun getItemCount(): Int = _movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_movies.get(position))
    }

    fun addData(movies: List<Movie>) {
        _movies.run {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    fun addGenres(genres: List<Genre>) {
        _genres.run {
            clear()
            addAll(genres)
        }
    }

    class ViewHolder(
        val itemHomeBinding: ItemHomeBinding,
        val genres: List<Genre>,
        onItemClick: (movie: Movie) -> Unit
    ) :
        RecyclerView.ViewHolder(itemHomeBinding.root) {

        init {
            itemHomeBinding.cardView.setOnClickListener {
                itemHomeBinding.Movie?.let(onItemClick)
            }
        }

        fun bind(movie: Movie) {
            itemHomeBinding.movie = Movie
            movie.genre_ids?.let {
                setGenres(itemHomeBinding.textViewMovieGenres, it, genres)
            }
        }

        fun setGenres(view: TextView, genresIds: List<Int>, genres: List<Genre>) {
            val builder = StringBuilder()
            genresIds.forEach {
                for (genre in genres) {
                    if (genre.id == it) {
                        builder.append("${genre.name}, ")
                        break
                    }
                }
            }
            view.text = builder.toString()
        }
    }
}
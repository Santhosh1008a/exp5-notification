package com.example.exp5notification.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.exp5notification.R
import com.example.exp5notification.data.Book
import com.example.exp5notification.data.BookRepository
import com.example.exp5notification.notifications.NotificationHelper

class HomeFragment : Fragment() {

    private lateinit var notificationHelper: NotificationHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationHelper = NotificationHelper(requireContext())

        val userName = activity?.intent?.getStringExtra("USER_NAME") ?: "Sandy"
        view.findViewById<TextView>(R.id.tv_welcome).text = "Good evening, $userName"

        setupContinueReading(view)
        setupGenres(view)
        
        notificationHelper.showContinueReadingNotification("The Hobbit", "Chapter 7")

        view.findViewById<View>(R.id.btn_logout).setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        view.findViewById<ImageView>(R.id.iv_home_bg).load("https://images.unsplash.com/photo-1549675584-91f19337af3d?q=80&w=1000&auto=format&fit=crop")
    }

    private fun setupContinueReading(view: View) {
        val rvContinue = view.findViewById<RecyclerView>(R.id.rv_continue_reading)
        rvContinue.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        
        val books = BookRepository.getContinueReadingBooks()
        rvContinue.adapter = ContinueReadingAdapter(books) { book ->
            val intent = Intent(requireContext(), BookDetailsActivity::class.java).apply {
                putExtra("BOOK_DATA", book)
            }
            startActivity(intent)
        }
    }

    private fun setupGenres(view: View) {
        val rvGenres = view.findViewById<RecyclerView>(R.id.rv_genres)
        rvGenres.layoutManager = GridLayoutManager(requireContext(), 2)
        
        val genres = listOf(
            Genre("Fiction", R.drawable.ic_book, R.color.sky_blue),
            Genre("Fantasy", R.drawable.ic_book, R.color.fantasy_purple),
            Genre("Mystery", R.drawable.ic_book, R.color.mystery_amber),
            Genre("Sci-Fi", R.drawable.ic_book, R.color.scifi_cyan),
            Genre("Romance", R.drawable.ic_book, R.color.romance_coral),
            Genre("Self-Help", R.drawable.ic_book, R.color.soft_cyan),
            Genre("Technology", R.drawable.ic_book, R.color.scifi_cyan),
            Genre("History", R.drawable.ic_book, R.color.history_gold),
            Genre("Classics", R.drawable.ic_book, R.color.history_gold),
            Genre("Academic", R.drawable.ic_book, R.color.sky_blue)
        )
        
        rvGenres.adapter = GenreAdapter(genres) { genre ->
            val intent = Intent(requireContext(), GenreDetailsActivity::class.java).apply {
                putExtra("GENRE_NAME", genre.name)
            }
            startActivity(intent)
        }
    }

    data class Genre(val name: String, val iconRes: Int, val colorRes: Int)

    inner class ContinueReadingAdapter(
        private val books: List<Book>,
        private val onClick: (Book) -> Unit
    ) : RecyclerView.Adapter<ContinueReadingAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val ivCover: ImageView = view.findViewById(R.id.iv_continue_cover)
            val tvTitle: TextView = view.findViewById(R.id.tv_continue_title)
            val tvAuthor: TextView = view.findViewById(R.id.tv_continue_author)
            val tvProgress: TextView = view.findViewById(R.id.tv_continue_progress)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_continue_reading, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val book = books[position]
            holder.ivCover.load(book.coverImage)
            holder.tvTitle.text = book.title
            holder.tvAuthor.text = book.author
            holder.tvProgress.text = "${book.progress}%"
            holder.itemView.setOnClickListener { onClick(book) }
        }

        override fun getItemCount() = books.size
    }

    inner class GenreAdapter(
        private val genres: List<Genre>,
        private val onClick: (Genre) -> Unit
    ) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tv_genre_name)
            val ivIcon: ImageView = view.findViewById(R.id.iv_genre_icon)
            val card: View = view.findViewById(R.id.card_genre)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val genre = genres[position]
            holder.tvName.text = genre.name
            holder.ivIcon.setImageResource(genre.iconRes)
            holder.card.setOnClickListener { onClick(genre) }
        }

        override fun getItemCount() = genres.size
    }
}

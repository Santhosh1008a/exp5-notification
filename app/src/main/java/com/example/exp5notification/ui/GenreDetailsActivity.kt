package com.example.exp5notification.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.exp5notification.R
import com.example.exp5notification.data.Book
import com.example.exp5notification.data.BookRepository

class GenreDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_details)

        val genreName = intent.getStringExtra("GENRE_NAME") ?: "Fiction"
        findViewById<TextView>(R.id.tv_genre_title).text = genreName

        findViewById<View>(R.id.btn_back).setOnClickListener { finish() }

        applyGenreTheme(genreName)
        setupBookList(genreName)
    }

    private fun applyGenreTheme(genre: String) {
        val root = findViewById<View>(R.id.genre_root)
        val gradient = findViewById<View>(R.id.genre_gradient)
        
        when (genre) {
            "Fantasy" -> gradient.setBackgroundResource(R.drawable.genre_gradient_fantasy)
            "Science Fiction" -> gradient.setBackgroundResource(R.drawable.genre_gradient_scifi)
            "Mystery & Thriller" -> gradient.setBackgroundResource(R.drawable.genre_gradient_mystery)
            else -> gradient.setBackgroundResource(R.drawable.genre_gradient_default)
        }
    }

    private fun setupBookList(genre: String) {
        val rvBooks = findViewById<RecyclerView>(R.id.rv_books)
        rvBooks.layoutManager = LinearLayoutManager(this)
        
        val books = BookRepository.getBooksByGenre(genre)
        rvBooks.adapter = BookAdapter(books) { book ->
            val intent = Intent(this, BookDetailsActivity::class.java).apply {
                putExtra("BOOK_DATA", book)
            }
            startActivity(intent)
        }
    }

    inner class BookAdapter(
        private val books: List<Book>,
        private val onClick: (Book) -> Unit
    ) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val ivCover: ImageView = view.findViewById(R.id.iv_book_cover)
            val tvTitle: TextView = view.findViewById(R.id.tv_book_title)
            val tvAuthor: TextView = view.findViewById(R.id.tv_book_author)
            val tvRating: TextView = view.findViewById(R.id.tv_book_rating)
            val ivFav: ImageView = view.findViewById(R.id.iv_favorite)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val book = books[position]
            holder.ivCover.load(book.coverImage)
            holder.tvTitle.text = book.title
            holder.tvAuthor.text = book.author
            holder.tvRating.text = book.rating.toString()
            
            holder.itemView.setOnClickListener { onClick(book) }
        }

        override fun getItemCount() = books.size
    }
}

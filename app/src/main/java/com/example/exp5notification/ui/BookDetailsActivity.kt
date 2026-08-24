package com.example.exp5notification.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exp5notification.notifications.NotificationHelper
import coil.load
import com.example.exp5notification.R
import com.example.exp5notification.data.Book

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        @Suppress("DEPRECATION")
        val book = intent.getParcelableExtra<Book>("BOOK_DATA")

        if (book == null) {
            finish()
            return
        }

        findViewById<View>(R.id.btn_detail_back).setOnClickListener { finish() }

        findViewById<ImageView>(R.id.iv_detail_cover).load(book.coverImage)
        findViewById<TextView>(R.id.tv_detail_title).text = book.title
        findViewById<TextView>(R.id.tv_detail_author).text = "By ${book.author}"
        findViewById<TextView>(R.id.tv_detail_genre).text = book.genre
        findViewById<TextView>(R.id.tv_detail_rating).text = book.rating.toString()
        findViewById<TextView>(R.id.tv_detail_year).text = book.publicationYear.toString()
        findViewById<TextView>(R.id.tv_detail_desc).text = book.description

        findViewById<Button>(R.id.btn_read_free).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.freeReadingUrl))
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_add_library).setOnClickListener {
            val notificationHelper = NotificationHelper(this)
            notificationHelper.showReadingReminderNotification(book.title, book.progress)
            Toast.makeText(this, "${book.title} added to your library", Toast.LENGTH_SHORT).show()
        }
        
        findViewById<View>(R.id.btn_detail_fav).setOnClickListener { view ->
            view.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).withEndAction {
                view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
            }.start()
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
        }
    }
}

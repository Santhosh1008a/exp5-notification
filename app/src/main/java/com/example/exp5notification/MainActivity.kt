package com.example.exp5notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exp5notification.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }
    }
}

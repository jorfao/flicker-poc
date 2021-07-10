package com.example.flickrpoc.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flickrpoc.R

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commitNow()
        }
    }
}
package com.example.datastoredemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var dataStorePrefrence: DataStorePrefrence
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         dataStorePrefrence = DataStorePrefrence(this)
         findViewById<View>(R.id.addButton).setOnClickListener {
                 view: View? ->
             count++
             GlobalScope.launch {
                 dataStorePrefrence.saveCount(count)

             }
         }
         val text = findViewById<TextView>(R.id.textCount)
         this.dataStorePrefrence.userCount.asLiveData().observe(this){
             text.text="$it"
         }
    }
}
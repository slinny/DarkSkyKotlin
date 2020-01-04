package com.example.android.darkskykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daily_recyclerview.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DailyAdapter()
        }
    }
}

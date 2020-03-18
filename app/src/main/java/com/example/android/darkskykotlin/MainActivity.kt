package com.example.android.darkskykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.darkskykotlin.adapter.DailyAdapter
import com.example.android.darkskykotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        city_textView.setText("New York")
        temp_textview.setText("50° F")

        daily_recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DailyAdapter()
        }
    }

//    private fun setCurrentIcon(resource: String) {
//        val imageRsc = WeatherIcons.getIconResource(resource)
//        this.current_icon_imageView.setImageResource(imageRsc)
//    }
}

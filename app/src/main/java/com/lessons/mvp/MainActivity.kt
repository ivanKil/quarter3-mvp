package com.lessons.mvp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.lessons.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private val mapButtons = HashMap<Int, Button>()

    private var vb: ActivityMainBinding? = null
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        mapButtons[0] = vb!!.btnCounter1
        mapButtons[1] = vb!!.btnCounter2
        mapButtons[2] = vb!!.btnCounter3

        mapButtons.keys.forEach { key ->
            mapButtons[key]!!.setOnClickListener { presenter.counterClick(key) }
        }
    }

    override fun setButtonText(index: Int, text: String) {
        mapButtons[index]?.text = text
    }
}

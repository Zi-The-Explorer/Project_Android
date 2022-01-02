package com.afauzi.androidsubmission

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetailDestination : AppCompatActivity() {

    private lateinit var buttonlike: Button
    private lateinit var buttonDntlike: Button

    private lateinit var tvName: TextView
    private lateinit var tvAbout: TextView
    private lateinit var ivDetail: ImageView

    companion object {
        //
        const val EXTRA_ABOUT = "extra_about"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_destination)

        tvName = findViewById(R.id.item_name_detail)
        tvAbout = findViewById(R.id.item_about_detail)
        ivDetail = findViewById(R.id.item_image_detail)

        buttonlike = findViewById(R.id.btn_like)
        buttonDntlike = findViewById(R.id.btn_DontLike)

        val nameDestinasi = intent.getStringExtra(EXTRA_NAME)
        val aboutDestinasi = intent.getStringExtra(EXTRA_ABOUT)
        val imageDetail = intent.getIntExtra(EXTRA_IMAGE, 0)

        tvName.text = nameDestinasi
        tvAbout.text = aboutDestinasi
        ivDetail.setImageResource(imageDetail)

        supportActionBar?.title = nameDestinasi

        val icLIke = findViewById<ImageView>(R.id.ic_like)
        icLIke.visibility = View.GONE

        buttonDntlike.visibility = View.GONE

        buttonlike.setOnClickListener {
            icLIke.visibility = View.VISIBLE
            buttonlike.visibility = View.GONE
            buttonDntlike.visibility = View.VISIBLE
//            Toast.makeText(this, "Kamu Menyukai $nameDestinasi", Toast.LENGTH_SHORT).show()
        }

        buttonDntlike.setOnClickListener {
            icLIke.visibility = View.GONE
            buttonlike.visibility = View.VISIBLE
            buttonDntlike.visibility = View.GONE
//            Toast.makeText(this, "Kamu Tidak Menyukai $nameDestinasi", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_account -> {
                startActivity(Intent(this, AccountActivity::class.java))
            }
        }
    }
}
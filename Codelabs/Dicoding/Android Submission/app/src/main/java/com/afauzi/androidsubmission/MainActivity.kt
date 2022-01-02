  package com.afauzi.androidsubmission

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.androidsubmission.adapter.AdapterDestination
import com.afauzi.androidsubmission.data.DataDestination
import com.afauzi.androidsubmission.model.ModelDestination

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName

    private lateinit var rvDestination: RecyclerView
    private var list: ArrayList<ModelDestination> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "GoTrip"

        rvDestination = findViewById(R.id.rv_destination)
        rvDestination.setHasFixedSize(true)

        list.addAll(DataDestination.listData)

        showDataListDestination()

    }

    private fun showSelectedDestination(destination: ModelDestination) {

//        Log.d(LOG_TAG, "Name Destination: ${destination.nameDestination}")

        val inten = Intent(this, DetailDestination::class.java)
        inten.putExtra(DetailDestination.EXTRA_NAME, destination.nameDestination)
        inten.putExtra(DetailDestination.EXTRA_ABOUT, destination.detailDestination)
        inten.putExtra(DetailDestination.EXTRA_IMAGE, destination.imageDestination)
        startActivity(inten)
    }

    private fun showDataListDestination() {
        rvDestination.layoutManager = LinearLayoutManager(this)
        val adapterDestination = AdapterDestination(list)
        rvDestination.adapter = adapterDestination


        adapterDestination.setOnItemClickCallBack(object : AdapterDestination.ClickItemCuy {
            override fun onItemClick(data: ModelDestination) {
                showSelectedDestination(data)
            }

        })
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
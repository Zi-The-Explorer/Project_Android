 package com.afauzi.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afauzi.recyclerview.adapter.CardHeroAdapter
import com.afauzi.recyclerview.adapter.GridHeroAdapter
import com.afauzi.recyclerview.adapter.ListHeroAdapter
import com.afauzi.recyclerview.model.ModelHero
import com.ib.custom.toast.CustomToastView

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes: RecyclerView

    private var list: ArrayList<ModelHero> = arrayListOf()

    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)


        list.addAll(HeroesData.listData)

        showRecyclerList()


    }

    private fun showSelectedHero(hero: ModelHero) {
        CustomToastView.makeText(this,
                Toast.LENGTH_SHORT, CustomToastView.DEFAULT,
                "Kamu Memilih" + hero.nameHero,
                false).show();


    }

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ModelHero) {
                showSelectedHero(data)
            }

        })
    }

    private fun showRecyclerGrid() {
        rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rvHeroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallBackCuy {
            override fun onItemClickedIniCuy(data: ModelHero) {
                showSelectedHero(data)
            }

        })

    }

    private fun showRecyclerCardView() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardHeroAdapter = CardHeroAdapter(list)
        rvHeroes.adapter = cardHeroAdapter
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
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }

            R.id.action_card_view -> {
                title = "Mode Card"
                showRecyclerCardView()
            }

            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }
        }
        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}
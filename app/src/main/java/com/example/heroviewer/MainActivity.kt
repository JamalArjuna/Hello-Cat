package com.example.heroviewer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.heroviewer.data.ArticleHero
import com.example.heroviewer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listData = arrayListOf<ArticleHero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.rvArticles.setHasFixedSize(true)
        listData.addAll(getListHeroes())
        showRecyler()
        Log.d("MainActivity", "listData size = ${listData.size}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_me -> {
                startActivity(Intent(this, AboutMeActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getListHeroes(): ArrayList<ArticleHero> {
        val dataName = resources.getStringArray(R.array.heroes_name)
        val dataDesc = resources.getStringArray(R.array.heroes_desc)
        val DataPhoto = resources.getStringArray(R.array.heroes_photo)
        val listHero = ArrayList<ArticleHero>()
        for (i in dataName.indices) {
            val hero = ArticleHero(dataName[i], dataDesc[i], DataPhoto[i])
            listHero.add(hero)
        }
        return listHero
    }

    fun showRecyler(){
        binding.rvArticles.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ArticleAdapter(listData)
        binding.rvArticles.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object: ArticleAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ArticleHero) {
                ShowSelectedHero(data)
            }
        })
    }

    private fun ShowSelectedHero(hero: ArticleHero){
        Toast.makeText(this, "kamu Memilih " + hero.title, Toast.LENGTH_SHORT).show()
    }
}
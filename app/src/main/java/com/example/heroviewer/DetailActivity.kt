package com.example.heroviewer

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.example.heroviewer.data.ArticleHero
import com.example.heroviewer.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val key_hero = "key_hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val article: ArticleHero? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(key_hero, ArticleHero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(key_hero)
        }
        if (article == null) {
            finish()
            return
        }
        binding.tvHeroName.text = article.title
        binding.tvHeroDescription.text = article.description
        loadImage(article.imageUrl)
    }

    private fun loadImage(photo: String?) {
        if (photo.isNullOrBlank()) {
            binding.ivHeroImage.setImageResource(R.drawable.placeholder)
            return
        }

        if (photo.startsWith("http://") || photo.startsWith("https://")) {
            Glide.with(this)
                .load(photo)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(binding.ivHeroImage)
            return
        }

        val resId = resources.getIdentifier(photo, "drawable", packageName)
        if (resId != 0) {
            Glide.with(this)
                .load(resId)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(binding.ivHeroImage)
            return
        }
        //fallback
        Glide.with(this)
            .load(photo)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .centerCrop()
            .into(binding.ivHeroImage)
    }
}

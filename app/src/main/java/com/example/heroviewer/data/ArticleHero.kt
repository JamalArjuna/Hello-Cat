package com.example.heroviewer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleHero(
    val title: String,
    val description: String,
    val imageUrl: String,
): Parcelable



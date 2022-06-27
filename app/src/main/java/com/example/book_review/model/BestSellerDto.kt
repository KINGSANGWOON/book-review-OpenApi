package com.example.book_review.model

import com.google.gson.annotations.SerializedName

data class BestSellerDto (
    @SerializedName("title") val title: String,
    @SerializedName("item") val books: List<Book>,

    // 일단 data class와
    // kotlin 형식의 interface를 공부해야겠다.
    // 제네릭



)
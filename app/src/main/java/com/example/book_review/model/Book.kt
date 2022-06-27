package com.example.book_review.model

import android.accounts.AuthenticatorDescription
import com.google.gson.annotations.SerializedName

data class Book (
    @SerializedName("itemId") val id: Long ,//server 에서 itmeId를 받아오도록 serializedName을 사용한 후 long에 mapping 해준다
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String
)
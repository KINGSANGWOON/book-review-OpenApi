package com.example.book_review.api

import com.example.book_review.model.BestSellerDto
import com.example.book_review.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET;
import retrofit2.http.Query;

interface BookService {

    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("query") keyword: String  //요구하는 기본 인자
    ): Call<SearchBookDto> //반환하고자 하는 값

    // 이 interface가 정의하는 것은 get 방식으로 저렇게 호출할때 저 함수에 key의 값과 query를 넣어주면 return 값으로 SearchBookDto를 반환해 준다는 것이다.

    @GET("/api/bestSeller.api?categoryId=100&output=json")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>
}

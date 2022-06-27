package com.example.book_review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_review.adapter.BookAdapter
import com.example.book_review.api.BookService
import com.example.book_review.databinding.ActivityMainBinding
import com.example.book_review.model.BestSellerDto
import com.example.book_review.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // 초기화

        initBookRecyclerView()

        setContentView(binding.root)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")  //https로 바꿔주는 것이 좋다
            .addConverterFactory(GsonConverterFactory.create())  // Json데이터를 사용자가 정의한 Java 객채로 변환해주는 라이브러리
            .build()
        // retrofit 구현체 생성
        // retrofit으로 우리가  만든 bookService를 구현할 수 있게 해주는 구현체이다.
        val bookService = retrofit.create(BookService::class.java)  //create로 BookService interface를 class 로 create 해준 뒤에

        bookService.getBestSellerBooks("4A87782420F67826C4F382A2E1E5AF4833BF9F5E2F3E30DB6A66AB4E6C6B3533")  //구현체와 interface가 함께 request를 보냈다. 필요로 하는 key 값토 가지고 //그리고 그 반환값을  queue에 넣는다는 함수 enequeue를 통해 넣는다.
            .enqueue(object: Callback<BestSellerDto>{ //반환값을 콜백인데 BestSellerDto의 형태를 가지고 있는 object이다.

                override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {  //성공 처리
                    if(response.isSuccessful.not()){  //만약 response의 값이 없으면 끝낸다.
                        Log.e(TAG,"NOT!! SUCCESS")
                        return
                    }
                    //body는 response의 BestSellerBto의 값을 뜻한다
                    var code:Int = response.code()
                    Log.d(TAG, "code: $code") // statue code를 볼 수 있음


                    response.body()?.let{
                        Log.d(TAG, it.toString())   //.let은 response.body의 값을 가지고 옴을 뜻함 즉 it = response.body()

                        it.books.forEach { book ->
                            Log.d(TAG,book.toString())

                        }
                        adapter.submitList(it.books) // current list가 book list로 변하여 출력

                    }


                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {  //실패 처리
                    Log.e(TAG,t.toString())
                }


            })

    }

    fun initBookRecyclerView(){
        adapter = BookAdapter()
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)  // 아이템의 배치를 담당함 linearlayoutmanager - 가로/세로,gridlayoutmanager - 그리드 형식,StaggeredGridLayoutManager -지그재그 형식의 그리드 형식
        binding.bookRecyclerView.adapter = adapter // 뷰를 뷰의 데이터에 바인딩한다
        //more = viewholder 각각의 뷰를 보관하는 holder 객체이다.
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}
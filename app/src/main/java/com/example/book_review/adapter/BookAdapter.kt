package com.example.book_review.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book_review.databinding.ItemBookBinding
import com.example.book_review.model.Book

class BookAdapter: ListAdapter<Book,BookAdapter.BookItemViewHolder>(diffUtil){
    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) { // itemBookBinding을 viewHolder의 itemView로 가지게 된다.
                                                                                                                  // 몇개 만들어져 있는 뷰를 뷰 홀더라고 한다 저 함수가 가르키는 것

        fun bind(bookModel: Book){
            binding.titleTextView.text = bookModel.title
            binding.descriptionTextView.text = bookModel.description

            Glide
                .with(binding.coverImageView.context) //이미지 담을 공간
                .load(bookModel.coverSmallUrl) //이미지 가져오기
                .into(binding.coverImageView) // 정확히 어디에 담을지 지정함
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {   // 항목에 사용할 뷰 생성
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))  // ItemBookBinding을 통해 클래스를 객체화 한 뒤 ItemBookBinding의 layout을 parent의 붙인다
    } //BookItemViewHolder를 반환함

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {  // 항목 뷰에 데이터를 연결하는 곳
        holder.bind(currentList[position])  // 어댑터가 가지고 있는 리스트중 [position]의 리스트
        //이미 Book 데이터의 리스트가 Int로 저장되어 있는데 그게 바로 position이다.
        // currentlist에 있는 position 인덱스의 bookmodel을 꺼내서 보낸다.
        //즉 onbind 함수가 호출이 되면 bind 함수가 실행되면서 titleTextView의 값을 넣어준다
        // 그냥 현재 보여지는 데이터를 넣어줘라는 느낌

    }

    // diffUtil은 새로운 값이 생겼을 때 항당할지 말지의 기준을 정함
    // 즉 recycle 재사용할지 말지는 정하는 기준


    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {  // 새로들어오는 newItem 값과  원래 있던 olditem 값을 비교한다
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}
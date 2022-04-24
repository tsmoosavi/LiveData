package com.example.livedata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.livedata.databinding.QuestionRecyclerviewBinding

class QuestionRecyclerAdapter:
    ListAdapter<QuestionEntity, QuestionRecyclerAdapter.ItemHolder>(QuestionDiffCallback) {
        class ItemHolder(val binding: QuestionRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ItemHolder {
        val binding: QuestionRecyclerviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.question_recyclerview,
            parent,false
        )
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_recyclerview, parent, false)

        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder:ItemHolder , position: Int) {
      holder.binding.question = getItem(position)
    }

    object QuestionDiffCallback : DiffUtil.ItemCallback<QuestionEntity>() {
        override fun areItemsTheSame(oldItem: QuestionEntity, newItem:QuestionEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: QuestionEntity, newItem: QuestionEntity): Boolean {
            return oldItem.questionText == newItem.questionText
            // this is true when you show all variable of doctor     return oldItem == newItem
        }
    }
}
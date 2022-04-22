package com.example.livedata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class QuestionRecyclerAdapter:
    ListAdapter<QuestionEntity, QuestionRecyclerAdapter.ItemHolder>(QuestionDiffCallback) {
        class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var questionText = view.findViewById<TextView>(R.id.questionText)
        var questionId = view.findViewById<TextView>(R.id.questionId)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_recyclerview, parent, false)

        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder:ItemHolder , position: Int) {
        holder.questionText.text = getItem(position).questionText
        holder.questionId.text = getItem(position).number.toString()
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
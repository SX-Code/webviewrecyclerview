package com.example.webview_recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.webview_recyclerview.R
import java.util.ArrayList

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecyclerViewHolder) {
            holder.textView.text = data[position]
        }
    }

    override fun getItemCount() = data.size

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.comment_tv)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<String>) {
        this.data = data
        notifyDataSetChanged()
    }

}
package com.tanidev.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanidev.R
import com.tanidev.core.domain.model.SearchResult
import kotlinx.android.synthetic.main.item_card_list_horizontal.view.*
import java.util.ArrayList

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ListViewHolder>() {
    private var listData = ArrayList<SearchResult>()
    var onItemClick: ((SearchResult) -> Unit)? = null

    fun setData(newListData: List<SearchResult>?) {
        if (newListData == null) return
        newListData.map {
            if (!listData.contains(it)) {
                listData.add(it)
            }
        }
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card_list_horizontal, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    fun clearData() {
        listData.clear()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: SearchResult) {
            with(itemView) {
                title.text = data.searchName
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
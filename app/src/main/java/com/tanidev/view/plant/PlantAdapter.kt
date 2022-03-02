package com.tanidev.view.plant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanidev.R
import com.tanidev.core.domain.model.Plant
import kotlinx.android.synthetic.main.item_card_list_horizontal.view.*
import java.util.ArrayList

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.ListViewHolder>() {

    private var listData = ArrayList<Plant>()
    var onItemClick: ((Plant) -> Unit)? = null

    fun setData(newListData: List<Plant>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_horizontal, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: PlantAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Plant) {
            with(itemView) {
                title.text = data.plantName
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
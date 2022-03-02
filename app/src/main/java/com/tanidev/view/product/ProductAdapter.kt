package com.tanidev.view.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanidev.R
import com.tanidev.core.domain.model.Product
import kotlinx.android.synthetic.main.item_card_list_horizontal.view.*
import java.util.ArrayList

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ListViewHolder>() {

    private var listData = ArrayList<Product>()
    var onItemClick: ((Product) -> Unit)? = null

    fun setData(newListData: List<Product>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_horizontal, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ProductAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Product) {
            with(itemView) {
                title.text = data.productName
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
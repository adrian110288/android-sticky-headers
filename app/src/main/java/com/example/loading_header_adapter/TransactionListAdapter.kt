package com.example.loading_header_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loading_header_adapter.lib.DateStickyHeadersAdapter
import com.example.loading_header_adapter.lib.StickyHeadersAdapter
import java.util.*

data class TransactionModel(
    val name: String,
    val date: Date
) : StickyHeadersAdapter.ItemModel

class TransactionListAdapter :
    DateStickyHeadersAdapter<TransactionModel, TransactionListAdapter.TransactionViewHolder>() {

    override fun getDate(model: TransactionModel): Date =
        model.date

    override fun onCreateItemViewHolder(parent: ViewGroup): TransactionViewHolder =
        TransactionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_item_layout, parent, false)
        )

    inner class TransactionViewHolder(view: View) :
        StickyHeadersItemViewHolder<TransactionModel>(view) {

        override fun bind(model: TransactionModel) {
            itemView.findViewById<TextView>(R.id.transaction_name_tv)
                .text = model.name
        }
    }
}
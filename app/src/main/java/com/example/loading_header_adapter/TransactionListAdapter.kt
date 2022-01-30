package com.example.loading_header_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loading_header_adapter.lib.DateStickyHeadersAdapter
import com.example.loading_header_adapter.lib.ItemModel
import com.example.loading_header_adapter.lib.ItemViewHolder
import java.util.*

data class TransactionModel(
    val name: String,
    val date: Date
) : ItemModel

class TransactionViewHolder(view: View) :
    ItemViewHolder<TransactionModel>(view) {

    override fun bind(model: TransactionModel) {
        itemView.findViewById<TextView>(R.id.transaction_name_tv)
            .text = model.name
    }
}

class TransactionListAdapter : DateStickyHeadersAdapter<TransactionModel, TransactionViewHolder>() {

    override fun getDate(position: Int, model: TransactionModel): Date =
        model.date

    override fun hasHeader(
        position: Int,
        previousModel: TransactionModel?,
        model: TransactionModel
    ): Boolean =
        previousModel == null || model.date != previousModel.date

    override fun onCreateItemViewHolder(parent: ViewGroup): TransactionViewHolder =
        TransactionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_item_layout, parent, false)
        )
}
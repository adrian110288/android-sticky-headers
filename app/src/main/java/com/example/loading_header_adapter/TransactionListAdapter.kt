package com.example.loading_header_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loading_header_adapter.lib.DateStickyHeadersAdapter
import com.example.loading_header_adapter.lib.StickyHeaderAdapter.StickyHeaderAdapterModel.ItemModel
import java.util.*

data class TransactionModel(
    val name: String,
    val date: Date
) : ItemModel

class TransactionListAdapter : DateStickyHeadersAdapter() {

    class TransactionViewHolder(view: View) :
        StickyHeaderAdapterViewHolder(view) {

        override fun bind(model: StickyHeaderAdapterModel) {
            val model = model as TransactionModel
            itemView.findViewById<TextView>(R.id.transaction_name_tv)
                .text = model.name
        }

        companion object {

            private const val LAYOUT_RES = R.layout.transaction_item_layout

            fun create(parent: ViewGroup) = TransactionViewHolder(
                LayoutInflater.from(parent.context).inflate(LAYOUT_RES, parent, false)
            )
        }
    }

    override fun getDate(position: Int, model: StickyHeaderAdapterModel): Date =
        (model as TransactionModel).date

    override fun hasHeader(
        position: Int,
        previousModel: StickyHeaderAdapterModel?,
        model: StickyHeaderAdapterModel
    ): Boolean {

        return if (previousModel == null) true
        else (model as TransactionModel).date != (previousModel as TransactionModel).date
    }

    override fun onCreateItemViewHolder(parent: ViewGroup): StickyHeaderAdapterViewHolder =
        TransactionViewHolder.create(parent)
}
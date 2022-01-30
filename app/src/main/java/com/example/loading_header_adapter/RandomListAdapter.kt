package com.example.loading_header_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loading_header_adapter.lib.LoadingStickyHeaderAdapter
import com.example.loading_header_adapter.lib.StickyHeaderAdapter

data class RandomHeaderItemModel(
    val title: String
) : StickyHeaderAdapter.StickyHeaderAdapterModel.HeaderModel

data class RandomItemModel(
    val title: String
) : StickyHeaderAdapter.StickyHeaderAdapterModel.ItemModel

class RandomListAdapter : LoadingStickyHeaderAdapter() {

    class HeaderViewHolder(view: View) : StickyHeaderAdapter.StickyHeaderAdapterViewHolder(view) {

        override fun bind(model: StickyHeaderAdapter.StickyHeaderAdapterModel) {
            val model = model as RandomHeaderItemModel
            itemView.findViewById<TextView>(R.id.header_tv)
                .text = model.title
        }
    }

    class ItemViewHolder(view: View) : StickyHeaderAdapter.StickyHeaderAdapterViewHolder(view) {
        override fun bind(model: StickyHeaderAdapter.StickyHeaderAdapterModel) {
            val model = model as RandomItemModel
            itemView.findViewById<TextView>(R.id.item_tv)
                .text = model.title
        }
    }

    override fun onCreateItemViewHolder(parent: ViewGroup): StickyHeaderAdapter.StickyHeaderAdapterViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun onCreateHeaderViewHolder(parent: ViewGroup): StickyHeaderAdapter.StickyHeaderAdapterViewHolder =
        HeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.header_layout, parent, false)
        )

}
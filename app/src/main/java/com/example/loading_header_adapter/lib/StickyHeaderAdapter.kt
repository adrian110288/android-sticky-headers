package com.example.loading_header_adapter.lib

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class StickyHeaderAdapter :
    ListAdapter<StickyHeaderAdapter.StickyHeaderAdapterModel, StickyHeaderAdapter.StickyHeaderAdapterViewHolder>(
        diffCallback
    ), StickyHeaders,
    StickyHeaders.ViewSetup {

    sealed interface StickyHeaderAdapterModel {
        interface HeaderModel : StickyHeaderAdapterModel
        interface ItemModel : StickyHeaderAdapterModel
    }

    companion object {

        const val HEADER_TYPE = 11
        const val ITEM_TYPE = 22

        val diffCallback = object : DiffUtil.ItemCallback<StickyHeaderAdapterModel>() {
            override fun areItemsTheSame(
                oldItem: StickyHeaderAdapterModel,
                newItem: StickyHeaderAdapterModel
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: StickyHeaderAdapterModel,
                newItem: StickyHeaderAdapterModel
            ): Boolean =
                oldItem.hashCode() == newItem.hashCode()

        }

    }

    abstract class StickyHeaderAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(model: StickyHeaderAdapterModel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StickyHeaderAdapterViewHolder =
        when (viewType) {
            HEADER_TYPE -> onCreateHeaderViewHolder(parent)
            ITEM_TYPE -> onCreateItemViewHolder(parent)
            else -> throw RuntimeException("")
        }

    protected abstract fun onCreateItemViewHolder(parent: ViewGroup): StickyHeaderAdapterViewHolder

    protected abstract fun onCreateHeaderViewHolder(parent: ViewGroup): StickyHeaderAdapterViewHolder

    override fun onBindViewHolder(holder: StickyHeaderAdapterViewHolder, position: Int) {
        when (getItem(position)) {
            is StickyHeaderAdapterModel.HeaderModel -> {
                val headerItem = getItem(position) as StickyHeaderAdapterModel.HeaderModel
                onBindHeaderViewHolder(holder, headerItem)
            }
            is StickyHeaderAdapterModel.ItemModel -> {
                val item = getItem(position) as StickyHeaderAdapterModel.ItemModel
                onBindItemViewHolder(holder, item)
            }
        }
    }

    open fun onBindHeaderViewHolder(
        holder: StickyHeaderAdapterViewHolder,
        model: StickyHeaderAdapterModel.HeaderModel
    ) {
        holder.bind(model)
    }

    open fun onBindItemViewHolder(
        holder: StickyHeaderAdapterViewHolder,
        model: StickyHeaderAdapterModel.ItemModel
    ) {
        holder.bind(model)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is StickyHeaderAdapterModel.HeaderModel -> HEADER_TYPE
        is StickyHeaderAdapterModel.ItemModel -> ITEM_TYPE
    }

    override fun isStickyHeader(position: Int): Boolean =
        getItem(position) is StickyHeaderAdapterModel.HeaderModel

    override fun setupStickyHeaderView(stickyHeader: View?) {
    }

    override fun teardownStickyHeaderView(stickyHeader: View?) {

    }
}

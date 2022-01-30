package com.example.loading_header_adapter.lib

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

sealed interface StickyHeaderAdapterModel
interface HeaderModel : StickyHeaderAdapterModel
interface ItemModel : StickyHeaderAdapterModel

abstract class StickyHeaderAdapterViewHolder<M : StickyHeaderAdapterModel>(view: View) :
    RecyclerView.ViewHolder(view) {
    abstract fun bind(model: M)
}

abstract class HeaderViewHolder<H : StickyHeaderAdapterModel>(view: View) :
    StickyHeaderAdapterViewHolder<H>(view) {
    abstract override fun bind(model: H)
}

abstract class ItemViewHolder<I : StickyHeaderAdapterModel>(view: View) :
    StickyHeaderAdapterViewHolder<I>(view) {
    abstract override fun bind(model: I)
}

abstract class StickyHeaderAdapter<H : HeaderModel, I : ItemModel, HVH : HeaderViewHolder<H>, IVH : ItemViewHolder<I>> :
    ListAdapter<StickyHeaderAdapterModel, StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel>>(
        diffCallback
    ), StickyHeaders,
    StickyHeaders.ViewSetup {

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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel> =
        when (viewType) {
            HEADER_TYPE -> onCreateHeaderViewHolder(parent) as StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel>
            ITEM_TYPE -> onCreateItemViewHolder(parent) as StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel>
            else -> throw RuntimeException("")
        }

    protected abstract fun onCreateHeaderViewHolder(parent: ViewGroup): HVH

    protected abstract fun onCreateItemViewHolder(parent: ViewGroup): IVH

    override fun onBindViewHolder(
        holder: StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel>,
        position: Int
    ) {
        when (getItem(position)) {
            is HeaderModel -> {
                val headerItem = getItem(position) as H
                onBindHeaderViewHolder(holder as HVH, headerItem)
            }
            is ItemModel -> {
                val item = getItem(position) as I
                onBindItemViewHolder(holder as IVH, item)
            }
        }
    }

    open fun onBindHeaderViewHolder(
        holder: HVH,
        model: H
    ) {
        holder.bind(model)
    }

    open fun onBindItemViewHolder(
        holder: IVH,
        model: I
    ) {
        holder.bind(model)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is HeaderModel -> HEADER_TYPE
        is ItemModel -> ITEM_TYPE
    }

    override fun isStickyHeader(position: Int): Boolean =
        getItem(position) is HeaderModel

    override fun setupStickyHeaderView(stickyHeader: View?) {
    }

    override fun teardownStickyHeaderView(stickyHeader: View?) {

    }

    val currentHeaders: List<H>
        get() = currentList.filterIsInstance(HeaderModel::class.java) as List<H>

    val getCurrentItems: List<I>
        get() = currentList.filterIsInstance(ItemModel::class.java) as List<I>
}

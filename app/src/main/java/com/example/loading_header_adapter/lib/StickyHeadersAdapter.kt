package com.example.loading_header_adapter.lib

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class StickyHeadersAdapter<H : StickyHeadersAdapter.HeaderModel, I : StickyHeadersAdapter.ItemModel, HVH : StickyHeadersAdapter.StickyHeaderViewHolder<H>, IVH : StickyHeadersAdapter.StickyHeadersItemViewHolder<I>> :
    ListAdapter<StickyHeadersAdapter.StickyHeadersAdapterModel, StickyHeadersAdapter.StickyHeadersAdapterViewHolder<StickyHeadersAdapter.StickyHeadersAdapterModel>>(
        diffCallback
    ), StickyHeaders,
    StickyHeaders.ViewSetup {

    val currentHeaders: List<H>
        get() = currentList.filterIsInstance(HeaderModel::class.java) as List<H>

    val currentItems: List<I>
        get() = currentList.filterIsInstance(ItemModel::class.java) as List<I>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel> =
        when (viewType) {
            HEADER_TYPE -> onCreateHeaderViewHolder(parent) as StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel>
            ITEM_TYPE -> onCreateItemViewHolder(parent) as StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel>
            else -> throw RuntimeException("")
        }

    protected abstract fun onCreateHeaderViewHolder(parent: ViewGroup): HVH

    protected abstract fun onCreateItemViewHolder(parent: ViewGroup): IVH

    override fun onBindViewHolder(
        holder: StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel>,
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

    sealed interface StickyHeadersAdapterModel
    interface HeaderModel : StickyHeadersAdapterModel
    interface ItemModel : StickyHeadersAdapterModel

    abstract class StickyHeadersAdapterViewHolder<M : StickyHeadersAdapterModel>(view: View) :
        RecyclerView.ViewHolder(view) {
        abstract fun bind(model: M)
    }

    abstract class StickyHeaderViewHolder<H : StickyHeadersAdapterModel>(view: View) :
        StickyHeadersAdapterViewHolder<H>(view) {
        abstract override fun bind(model: H)
    }

    abstract class StickyHeadersItemViewHolder<I : StickyHeadersAdapterModel>(view: View) :
        StickyHeadersAdapterViewHolder<I>(view) {
        abstract override fun bind(model: I)
    }

    companion object {

        const val HEADER_TYPE = 11
        const val ITEM_TYPE = 22

        val diffCallback = object : DiffUtil.ItemCallback<StickyHeadersAdapterModel>() {
            override fun areItemsTheSame(
                oldItem: StickyHeadersAdapterModel,
                newItem: StickyHeadersAdapterModel
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: StickyHeadersAdapterModel,
                newItem: StickyHeadersAdapterModel
            ): Boolean =
                oldItem.hashCode() == newItem.hashCode()

        }

    }
}

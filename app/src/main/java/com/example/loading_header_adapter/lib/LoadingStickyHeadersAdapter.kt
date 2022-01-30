package com.example.loading_header_adapter.lib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.loading_header_adapter.R

abstract class LoadingStickyHeadersAdapter<H : StickyHeadersAdapter.HeaderModel, I : StickyHeadersAdapter.ItemModel, HVH : StickyHeadersAdapter.StickyHeaderViewHolder<H>, IVH : StickyHeadersAdapter.StickyHeadersItemViewHolder<I>> :
    StickyHeadersAdapter<H, I, HVH, IVH>() {

    class LoadingViewHolder(view: View) :
        StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel>(view) {
        override fun bind(model: StickyHeadersAdapterModel) {}
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel> {
        return if (viewType == LOADING_TYPE) onCreateLoadingViewHolder(parent)
        else super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel>,
        position: Int
    ) {
        if (holder is LoadingViewHolder) onBindLoadingViewHolder(holder)
        else super.onBindViewHolder(holder, position)
    }

    private fun onCreateLoadingViewHolder(parent: ViewGroup): StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel> {
        return LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(getLoadingItemLayout(), parent, false)
        )
    }

    open fun onBindLoadingViewHolder(
        holder: StickyHeadersAdapterViewHolder<StickyHeadersAdapterModel>
    ) {
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLast(position) && loading) LOADING_TYPE
        else super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return if (loading) super.getItemCount() + 1 else super.getItemCount()
    }

    override fun isStickyHeader(position: Int): Boolean {
        return if (position > itemCount - 1) false
        else if (isLast(position) && loading) false
        else super.isStickyHeader(position)
    }

    private fun isLast(position: Int): Boolean =
        position == itemCount - 1

    @LayoutRes
    open fun getLoadingItemLayout(): Int = R.layout.loading_item

    var loading: Boolean = false
        set(value) {
            field = value
            if (value) notifyItemInserted(itemCount)
            else notifyItemRemoved(itemCount)
        }

    companion object {
        const val LOADING_TYPE = 33
    }
}
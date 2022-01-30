package com.example.loading_header_adapter.lib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.loading_header_adapter.R

abstract class LoadingStickyHeaderAdapter<H : HeaderModel, I : ItemModel, HVH : HeaderViewHolder<H>, IVH : ItemViewHolder<I>> :
    StickyHeaderAdapter<H, I, HVH, IVH>() {

    class LoadingViewHolder(view: View) :
        StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel>(view) {
        override fun bind(model: StickyHeaderAdapterModel) {}
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel> {
        return if (viewType == LOADING_TYPE) onCreateLoadingViewHolder(parent)
        else super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel>,
        position: Int
    ) {
        if (holder is LoadingViewHolder) onBindLoadingViewHolder(holder)
        else super.onBindViewHolder(holder, position)
    }

    private fun onCreateLoadingViewHolder(parent: ViewGroup): StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel> {
        return LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(getLoadingItemLayout(), parent, false)
        )
    }

    open fun onBindLoadingViewHolder(
        holder: StickyHeaderAdapterViewHolder<StickyHeaderAdapterModel>
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
package com.example.loading_header_adapter.lib

abstract class AutoStickyHeadersAdapter<H : HeaderModel, I : ItemModel, HVH : HeaderViewHolder<H>, IVH : ItemViewHolder<I>> :
    LoadingStickyHeaderAdapter<H, I, HVH, IVH>() {

    fun submitItems(list: List<I>?) {
        list ?: return

        val listWithHeaders = mutableListOf<StickyHeaderAdapterModel>()

        val itemOnlySubmittedList: List<I> = list
            .filter { it !is HeaderModel }

        itemOnlySubmittedList.forEachIndexed { index, model ->

            val hasHeader = hasHeader(index, itemOnlySubmittedList.getOrNull(index - 1), model)

            if (hasHeader) listWithHeaders.add(getHeaderModel(index, model))
            listWithHeaders.add(model)
        }

        super.submitList(listWithHeaders)
    }

    abstract fun hasHeader(
        position: Int,
        previousModel: I?,
        model: I
    ): Boolean

    abstract fun getHeaderModel(position: Int, model: I): H

}
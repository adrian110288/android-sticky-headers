package com.example.loading_header_adapter.lib

import com.example.loading_header_adapter.lib.StickyHeaderAdapter.StickyHeaderAdapterModel.HeaderModel

abstract class AutoStickyHeadersAdapter : LoadingStickyHeaderAdapter() {

    override fun submitList(list: List<StickyHeaderAdapterModel>?) {
        list ?: return

        val listWithHeaders = mutableListOf<StickyHeaderAdapterModel>()

        val itemOnlySubmittedList = list
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
        previousModel: StickyHeaderAdapterModel?,
        model: StickyHeaderAdapterModel
    ): Boolean

    abstract fun getHeaderModel(position: Int, model: StickyHeaderAdapterModel): HeaderModel

}
package com.example.loading_header_adapter.lib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loading_header_adapter.R
import java.text.SimpleDateFormat
import java.util.*

data class DateHeaderModel(val dateFormatted: String) :
    StickyHeaderAdapter.StickyHeaderAdapterModel.HeaderModel

abstract class DateStickyHeadersAdapter : AutoStickyHeadersAdapter() {

    private val dateFormatter by lazy { SimpleDateFormat(getHeaderDateFormat()) }

    class DateHeaderViewHolder(view: View) :
        StickyHeaderAdapter.StickyHeaderAdapterViewHolder(view) {

        override fun bind(model: StickyHeaderAdapterModel) {
            val model = model as DateHeaderModel
            itemView.findViewById<TextView>(R.id.header_tv)
                .text = model.dateFormatted
        }

        companion object {

            private const val LAYOUT_RES = R.layout.header_layout

            fun create(parent: ViewGroup) = DateHeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(LAYOUT_RES, parent, false)
            )
        }
    }

    override fun getHeaderModel(
        position: Int,
        model: StickyHeaderAdapterModel
    ): StickyHeaderAdapterModel.HeaderModel =
        DateHeaderModel(getFormattedDate(getDate(position, model)))

    override fun onCreateHeaderViewHolder(parent: ViewGroup): StickyHeaderAdapterViewHolder {
        return DateHeaderViewHolder.create(parent)
    }

    abstract fun getDate(position: Int, model: StickyHeaderAdapterModel): Date

    open fun getHeaderDateFormat(): String = "d MMM yyyy"

    open fun getFormattedDate(date: Date): String = dateFormatter.format(date)
}
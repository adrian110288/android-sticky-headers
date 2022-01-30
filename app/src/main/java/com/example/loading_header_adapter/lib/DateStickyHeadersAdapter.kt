package com.example.loading_header_adapter.lib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loading_header_adapter.R
import java.text.SimpleDateFormat
import java.util.*

data class DateHeaderModel(val date: Date, val formattedDate: String) :
    StickyHeadersAdapter.HeaderModel

abstract class DateStickyHeadersAdapter<I : StickyHeadersAdapter.ItemModel, IVH : StickyHeadersAdapter.StickyHeadersItemViewHolder<I>> :
    AutoStickyHeadersAdapter<DateHeaderModel, I, DateStickyHeadersAdapter.DateStickyHeaderViewHolder, IVH>() {

    private val dateFormatter by lazy { SimpleDateFormat(getHeaderDateFormat()) }

    override fun getHeaderModel(
        position: Int,
        model: I
    ): DateHeaderModel = with(getDate(model)) {
        DateHeaderModel(this, getFormattedDate(this))
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): DateStickyHeaderViewHolder =
        DateStickyHeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.header_layout, parent, false)
        )

    override fun hasHeader(position: Int, previousModel: I?, model: I): Boolean =
        previousModel == null || getDate(model) != getDate(previousModel)

    abstract fun getDate(model: I): Date

    open fun getHeaderDateFormat(): String = "d MMM yyyy"

    open fun getFormattedDate(date: Date): String = dateFormatter.format(date)

    class DateStickyHeaderViewHolder(view: View) :
        StickyHeadersAdapter.StickyHeaderViewHolder<DateHeaderModel>(view) {

        override fun bind(model: DateHeaderModel) {
            itemView.findViewById<TextView>(R.id.header_tv).text = model.formattedDate
        }
    }
}
package com.example.loading_header_adapter.lib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.loading_header_adapter.R
import java.text.SimpleDateFormat
import java.util.*

data class DateHeaderModel(val date: Date, val formattedDate: String) : HeaderModel

class DateHeaderViewHolder(view: View) :
    HeaderViewHolder<DateHeaderModel>(view) {

    override fun bind(model: DateHeaderModel) {
        itemView.findViewById<TextView>(R.id.header_tv).text = model.formattedDate
    }
}

abstract class DateStickyHeadersAdapter<I : ItemModel, IVH : ItemViewHolder<I>> :
    AutoStickyHeadersAdapter<DateHeaderModel, I, DateHeaderViewHolder, IVH>() {

    private val dateFormatter by lazy { SimpleDateFormat(getHeaderDateFormat()) }

    override fun getHeaderModel(
        position: Int,
        model: I
    ): DateHeaderModel = with(getDate(position, model)) {
        DateHeaderModel(this, getFormattedDate(this))
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): DateHeaderViewHolder =
        DateHeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.header_layout, parent, false)
        )

    abstract fun getDate(position: Int, model: I): Date

    open fun getHeaderDateFormat(): String = "d MMM yyyy"

    open fun getFormattedDate(date: Date): String = dateFormatter.format(date)
}
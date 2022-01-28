package com.example.loading_header_adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.loading_header_adapter.lib.StickyHeaderAdapter
import com.example.loading_header_adapter.lib.StickyHeadersLinearLayoutManager

class MainActivity : AppCompatActivity() {
    val listAdapter: StickyHeaderAdapter = RandomListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(findViewById<RecyclerView>(R.id.list)) {
            val layoutManager =
                StickyHeadersLinearLayoutManager<StickyHeaderAdapter>(
                    context
                )
            this.layoutManager = layoutManager
            this.adapter = listAdapter
        }

        val list = listOf(
            RandomHeaderItemModel("Header 1"),
            RandomItemModel("Item 1"),
            RandomItemModel("Item 2"),
            RandomItemModel("Item 3"),
            RandomItemModel("Item 4"),
            RandomHeaderItemModel("Header 2"),
            RandomItemModel("Item 5"),
            RandomItemModel("Item 6"),
            RandomItemModel("Item 7"),
            RandomHeaderItemModel("Header 3"),
            RandomItemModel("Item 8"),
            RandomItemModel("Item 9"),
            RandomItemModel("Item 10"),
            RandomHeaderItemModel("Header 4"),
            RandomItemModel("Item 11"),
            RandomItemModel("Item 12"),
            RandomItemModel("Item 13"),
            RandomHeaderItemModel("Header 5"),
            RandomItemModel("Item 14"),
            RandomItemModel("Item 15"),
            RandomItemModel("Item 16"),
            RandomHeaderItemModel("Header 6"),
            RandomItemModel("Item 17"),
            RandomItemModel("Item 18"),
            RandomItemModel("Item 19"),
            RandomHeaderItemModel("Header 7"),
            RandomItemModel("Item 20"),
            RandomItemModel("Item 21"),
            RandomItemModel("Item 22"),
            RandomHeaderItemModel("Header 8"),
            RandomItemModel("Item 23"),
            RandomItemModel("Item 24"),
            RandomItemModel("Item 25")
        )



        listAdapter.submitList(list)

    }
}

//class StickyHeaderAdapter: ListAdapter<StickyHeaderAdapterModel, StickyHeaderAdapter.StickyHeaderAdapterViewHolder>(), StickyHeaders, StickyHeaders.ViewSetup {
//
//    open inner class StickyHeaderAdapterViewHolder(view: View): RecyclerView.ViewHolder(view)
//
//    inner class ItemHolder(view: View): RecyclerView.ViewHolder(view)
//    inner class HeaderHolder(view: View): RecyclerView.ViewHolder(view)
//
//    var items: List<StickyHeaderAdapterModel> by Delegates.observable(emptyList()) { _, _, _ ->
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
//        when(viewType) {
//            R.layout.header_layout -> HeaderHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
//            R.layout.item_layout -> ItemHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
//            else -> throw RuntimeException("")
//        }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when(holder) {
//            is HeaderHolder -> {
//                val headerItem = items[position] as StickyHeaderModel
//                holder.itemView.findViewById<TextView>(R.id.header_tv).text = headerItem.title
//            }
//            is ItemHolder -> {
//                val item = items[position] as ListItemModelSticky
//                holder.itemView.findViewById<TextView>(R.id.item_tv).text = item.title
//            }
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int = when(items[position]) {
//        is HeaderModel -> R.layout.header_layout
//        is ItemModel -> R.layout.item_layout
//    }
//
//    override fun getItemCount(): Int = items.size
//
//    override fun isStickyHeader(position: Int): Boolean =
//        items[position] is HeaderModel
//
//    override fun setupStickyHeaderView(stickyHeader: View?) {
//        Log.i("", "")
//    }
//
//    override fun teardownStickyHeaderView(stickyHeader: View?) {
//
//    }
//}
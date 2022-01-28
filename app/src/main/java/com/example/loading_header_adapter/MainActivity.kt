package com.example.loading_header_adapter

import android.os.Bundle
import android.widget.Button
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


        var seq = 0
        val addMore: Button = findViewById<Button>(R.id.add_more)
        addMore.setOnClickListener {
            seq++
            listAdapter.submitList(listAdapter.currentList + getList(seq).toList())
        }
    }
}

fun MainActivity.getList(seq: Int): List<StickyHeaderAdapter.StickyHeaderAdapterModel> =
    listOf(
        RandomHeaderItemModel("Header $seq"),
        RandomItemModel("Item ${seq * seq}"),
        RandomItemModel("Item ${seq * seq + 1}"),
        RandomItemModel("Item ${seq * seq + 2}")
    )
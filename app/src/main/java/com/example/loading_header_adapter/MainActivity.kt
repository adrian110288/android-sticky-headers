package com.example.loading_header_adapter

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.loading_header_adapter.lib.StickyHeaderAdapter
import com.example.loading_header_adapter.lib.StickyHeaderAdapter.StickyHeaderAdapterModel
import com.example.loading_header_adapter.lib.StickyHeadersLinearLayoutManager
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    //    val listAdapter = RandomListAdapter()
    val transactionsAdapter = TransactionListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(findViewById<RecyclerView>(R.id.list)) {
            val layoutManager =
                StickyHeadersLinearLayoutManager<StickyHeaderAdapter>(
                    context
                )
            this.layoutManager = layoutManager
//            this.adapter = listAdapter
            this.adapter = transactionsAdapter
        }

        transactionsAdapter.submitList(getTransactions())
        var seq = 0
        val addMore: Button = findViewById(R.id.add_more)
        addMore.setOnClickListener {
            seq++
            transactionsAdapter.submitList(
                transactionsAdapter.currentList + getTransactions2().toList()
            )
        }
        val setLoading: Button = findViewById(R.id.set_loading)
        setLoading.setOnClickListener {
            transactionsAdapter.loading = !transactionsAdapter.loading
        }
    }
}

fun MainActivity.getList(seq: Int): List<StickyHeaderAdapterModel> =
    listOf(
        RandomHeaderItemModel("Header $seq"),
        RandomItemModel("Item ${seq * seq}"),
        RandomItemModel("Item ${seq * seq + 1}"),
        RandomItemModel("Item ${seq * seq + 2}")
    )

fun MainActivity.getTransactions(): List<StickyHeaderAdapterModel> =
    listOf(
        TransactionModel("Transactions 1", "12-12-2021".toDate()),
        TransactionModel("Transactions 11", "12-12-2021".toDate()),
        TransactionModel("Transactions 2", "12-09-2021".toDate()),
        TransactionModel("Transactions 22", "12-09-2021".toDate()),
        TransactionModel("Transactions 222", "12-09-2021".toDate()),
        TransactionModel("Transactions 3", "1-07-2021".toDate()),
        TransactionModel("Transactions 4", "25-08-2021".toDate()),
        TransactionModel("Transactions 4", "25-08-2021".toDate()),
        TransactionModel("Transactions 5", "10-05-2021".toDate()),
        TransactionModel("Transactions 55", "10-05-2021".toDate()),
        TransactionModel("Transactions 555", "10-05-2021".toDate()),
        TransactionModel("Transactions 5555", "10-05-2021".toDate()),
        TransactionModel("Transactions 6", "3-04-2021".toDate()),
        TransactionModel("Transactions 7", "22-02-2021".toDate()),
        TransactionModel("Transactions 7", "22-02-2021".toDate()),
        TransactionModel("Transactions 8", "10-01-2021".toDate()),
        TransactionModel("Transactions 88", "10-01-2021".toDate()),
    )

fun MainActivity.getTransactions2(): List<StickyHeaderAdapterModel> =
    listOf(
        TransactionModel("Transactions 888", "10-01-2021".toDate()),
        TransactionModel("Transactions 9", "09-01-2021".toDate()),
        TransactionModel("Transactions 99", "09-01-2021".toDate()),
        TransactionModel("Transactions 10", "05-01-2021".toDate()),
        TransactionModel("Transactions 101", "05-01-2021".toDate()),
        TransactionModel("Transactions 1011", "05-01-2021".toDate()),
        TransactionModel("Transactions 10111", "05-01-2021".toDate()),
        TransactionModel("Transactions 101111", "05-01-2021".toDate()),
    )

fun String.toDate() = SimpleDateFormat("dd-MM-yyyy").parse(this)
package id.co.coffeecode.learnsqliteandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListActivity : AppCompatActivity() {

    private lateinit var adapter: RVAdapter
    private var santriList = ArrayList<Santri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        adapter = RVAdapter(this, santriList)
        rvList.adapter = adapter

        getData()
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        database.use {
            santriList.clear()
            val result = select(Santri.TABLE_SANTRI)
            val dataSantri = result.parseList(classParser<Santri>())

            santriList.addAll(dataSantri)
            adapter.notifyDataSetChanged()
        }
    }
}

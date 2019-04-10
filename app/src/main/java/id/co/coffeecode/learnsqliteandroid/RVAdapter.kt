package id.co.coffeecode.learnsqliteandroid

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RVAdapter(private val context: Context, private val items: ArrayList<Santri>)
    : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, p0, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(items[p1])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindItem(items: Santri){
            itemView.namaSantri.text = items.nama
            itemView.alamatSantri.text = items.alamat
            itemView.handphoneSantri.text = items.handphone

            itemView.btnEdit.setOnClickListener {
                itemView.context.startActivity<MainActivity>(
                        "oldNama" to items.nama,
                        "oldAlamat" to items.alamat,
                        "oldHanphone" to items.handphone
                )
            }

            itemView.btnHapus.setOnClickListener {
                itemView.context.database.use {
                    delete(Santri.TABLE_SANTRI,"${Santri.ID} = {id}",
                            "id" to items.id.toString())
                }
                itemView.context.startActivity<MainActivity>()
                itemView.context.toast("Data dihapus")
            }
        }
    }

}
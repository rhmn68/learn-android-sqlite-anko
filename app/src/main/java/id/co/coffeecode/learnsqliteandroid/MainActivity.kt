package id.co.coffeecode.learnsqliteandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oldNama = intent.getStringExtra("oldNama")
        val oldAlamat = intent.getStringExtra("oldAlamat")
        val oldHandphone = intent.getStringExtra("oldHanphone")

        if (oldAlamat.isNullOrBlank()){
            buttonUpdate.isEnabled = false
        }else{
            buttonSimpan.isEnabled = false
            editTextNama.setText(oldNama)
            editTextAlamat.setText(oldAlamat)
            editTextNomorHandphone.setText(oldHandphone)
        }

        buttonSimpan.setOnClickListener{
            addDataSantri()
            clearData()
        }

        buttonLihatData.setOnClickListener {
            startActivity<ListActivity>()
        }

        buttonUpdate.setOnClickListener {
            updateData(oldNama)
            //Clear Data
            clearData()
            toast("Data Diupdate")
        }
    }

    private fun updateData(oldNama: String) {
        database.use {
            update(Santri.TABLE_SANTRI,
                    Santri.NAMA to editTextNama.text.toString(),
                    Santri.ALAMAT to editTextAlamat.text.toString(),
                    Santri.HANDPHONE to editTextNomorHandphone.text.toString())
                    .whereArgs("${Santri.NAMA} = {nama}",
                            "nama" to oldNama)
                    .exec()
        }
    }

    private fun addDataSantri() {
        database.use {
            insert(Santri.TABLE_SANTRI,
                    Santri.NAMA to editTextNama.text.toString(),
                    Santri.ALAMAT to editTextAlamat.text.toString(),
                    Santri.HANDPHONE to editTextNomorHandphone.text.toString())


            toast("Data Berhasil Disimpan")
        }
    }

    private fun clearData(){
        editTextNama.text.clear()
        editTextNomorHandphone.text.clear()
        editTextAlamat.text.clear()
    }
}

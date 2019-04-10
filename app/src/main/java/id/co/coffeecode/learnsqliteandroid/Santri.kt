package id.co.coffeecode.learnsqliteandroid

data class Santri(var id: Long?, var nama:String?, var alamat: String?, var handphone: String?){
    companion object {
        const val TABLE_SANTRI = "TABLE_SANTRI"
        const val ID = "ID_"
        const val NAMA = "NAMA"
        const val ALAMAT = "ALAMAT"
        const val HANDPHONE = "HANDPHONE"
    }
}
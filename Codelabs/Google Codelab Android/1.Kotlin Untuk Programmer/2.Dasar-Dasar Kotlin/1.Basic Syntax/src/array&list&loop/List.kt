package `array&list&loop`

fun main() {
    /**
     * Deklarasi daftar menggunakan listOf
     * secara default listOf bertipe immutable
     * Tidak dapat diubah
     */
    val school = listOf("mackerel", "trout", "halibut")
    println(school)
    println()

    /**
     * Deklarasi daftar yang dapat diubah menggunakan mutableListOf
     * Menghapus item meggunakan metode remove
     */
    val myList = mutableListOf("tuna", "salmon", "shark")
    myList.remove("shark") // Metode remove akan mengembalikan true ketika berhasil menghapus item
    println(myList)
}
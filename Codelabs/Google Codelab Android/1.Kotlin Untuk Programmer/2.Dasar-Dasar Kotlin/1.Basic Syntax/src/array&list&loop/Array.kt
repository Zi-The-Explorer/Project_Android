package `array&list&loop`

fun main() {
    /**
     * Mendeklarasikan array string menggunakan arrayOf
     * Gunakan java.util.Arrays.toString() untuk menampilkan isi array
     * dengan mendefinisikan array ke variable bertipe val, maka tidak bisa diubah
     */
    val shcool = arrayOf("shark", "salmon", "minnow")
    println(java.util.Arrays.toString(shcool))
    println()

    /**
     * Pencampuran tipe data didalam array
     * Mendeklarasikan array dengan tipe data yang berbeda
     */
    val mix = arrayOf("fish", 2)

    /**
     * Mendeklarasikan satu tipe untuk semua elemen array
     * Mendeklarasikan array dengan tipe data bilangan bulat menggunakan intOfArray()
     */
    val numbers = intArrayOf(1, 2, 3)

    /**
     * Menggabungkan 2 array dengan operator +
     */
    val numbers3 = intArrayOf(4, 5, 6)
    val foo2 = numbers + numbers3
    println(foo2[5])
    println()

    /**
     * Mendeklarasikan array didalam array
     */
    val oceans = listOf("Atlantic", "Pacific")
    val oddList = listOf(numbers, oceans, "salmon")
    println(oddList)
    println()

    /**
     * Salah satu fitur bagus dari Kotlin
     * adalah Anda dapat menginisialisasi array dengan kode alih-alih menginisialisasinya ke 0
     */
    val array = Array(5) { it * 2 }
    println(java.util.Arrays.toString(array))
}
package com.afauzi.libs.kotlin.kotlinBasic

fun main() {

//    Todo => Array tidak seperti List di kotlin yang memiliki versi yang dapat berubah (mutable) dan tidak dapat diubah (immutable), di array tidak ada

    /**
     * Deklarasi array string menggunakan arrayOf()
     *
     * Gunakan java.util.Arrays.toString() utilitas array untuk mencetak nya
     */
    val school = arrayOf("shark", "salmon", "minnow")
    println(java.util.Arrays.toString(school))

    /**
     * Menggabungkan data dengan type data yang berbeda didalam array
     */
    val mix = arrayOf("fish", 2)

    /**
     * Menggunakan array dengan hanya satu tipe data
     *
     * Deklarasi Array dengan bilangan bulat menggunakan intArrayOf()
     */
    val numbers = intArrayOf(1,2,3)

    /**
     * Menggabungkan 2 array dengan menggunakan operator tambah (+)
     */
    val numbers1 = intArrayOf(1,2,3)
    val numbers2 = intArrayOf(4,5,6)
    val foo = numbers1 + numbers2
    println(foo[2]) // Output (3)

    /**
     * Deklarasi kombinasi array dan list
     *
     * Element pertama dalam daftar oddList adalah num "Array",
     * saat array di akses tidak menggunakan utilitas java untuk mencetaknya, maka akan ditampilkan data yang tidak dapat dibaca
     */
    val num = intArrayOf(1,2,3)
    val oceans = listOf("Atlantic", "Pacific")
    val oddList = listOf(num, oceans, "Salmon")
    println(oddList)

    /**
     * Salah satu fitur bagus dari kotlin adalah, dapat menginisialisasi array dengan kode alih alih menginisialisasi nya ke 0
     *
     * Kode ini akan membuatkan element pada array dengan jumlah 5 data
     * lalu pada lambda expression memberikan steatment bahwa jumlah array "5" dikalikan 5
     * maka hasil output adalah angka yang di kalikan dari jumlah seluruh data dalam array dengan jumlah data yang sudah di inisialisasikan dalam parameter  size "5"
     */
    val array = Array (5) {it * 5}
    println(java.util.Arrays.toString(array))

}
package com.afauzi.libs.kotlin.kotlinBasic

fun main() {


//    Todo => Secara default, variable tidak boleh null

    /**
     * Deklarasi variable bertype int dan menetapkan null sebagai nilai nya
     * Error variable
     */
//    var rocks: Int = null // => error: null can not be a value of a non-null type Int

    /**
     * Menggunakan tanda tanya ? setelah type data untuk mengatasi nilai null
     */
    val marbles: Int? = null


    /**
     * Pengecekan nilai null secara manual
     */
    var fishFoodTreats = 6
    if (fishFoodTreats != null) {
        fishFoodTreats = fishFoodTreats.dec() // Fungsi dec() adalah fungsi untuk mengurangi 1 nilai atau decrement
        println(fishFoodTreats)
    }

    /**
     * Pengecekan nilai null yang sudah disediakan kotlin
     */
    var fishFoodTreats1 = 6
    fishFoodTreats1 = fishFoodTreats1?.dec()
    println(fishFoodTreats1)


    /**
     * Pengujian null dengan elvis operator
     *
     * Ini adalah singkatan dari "jika fishFoodTreatstidak null, kurangi dan gunakan; jika tidak, gunakan nilai setelah ?:, yaitu 0." Jika fishFoodTreatsya null, evaluasi dihentikan, dan dec()metode tidak dipanggil.
     */
    var fishFoodTreats3: Any? = "Fish"
    fishFoodTreats3 = fishFoodTreats3 ?: "Nilai Null"
    println(fishFoodTreats3)

    /**
     * Null Safe Atau keamanan null
     *
     * Fitur kotlin ini untuk  programmer java yang masih menyukai nullpointer EXception
     *
     * wajib menggunakan Tanda Tanya dua kali !! sebelum nilai itu di konversi
     */
    val s: String? = null
    val len = s!!.length
    println(len)

}
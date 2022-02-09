package com.afauzi.libs.kotlin.kotlinBasic

fun main() {

//    Todo => Daftar / List adalah tipe dasar di kotlin, dan mirip dengan list dibahasa lain

    /**
     * Deklarasi daftar menggunakan listOf()
     * Daftar ini tidak dapat diubah "immutable"
     */
    val school = listOf("mackeral", "trout", "halibut")
    println(school)

    /**
     * Deklarasi daftar yang dapat diubah menggunakan mutableListOf() digunakan untuk merubah data
     *
     * ini adalah contoh untuk menghapus data
     */
    val myList = mutableListOf("tuna", "salmon", "shark")
    myList.remove("shark") // => Menghapus item dari myList berdasarkan element String
    println(myList)



}
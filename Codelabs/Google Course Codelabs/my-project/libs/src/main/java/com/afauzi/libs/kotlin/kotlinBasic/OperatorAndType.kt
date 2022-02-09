package com.afauzi.libs.kotlin.kotlinBasic

fun main() {

    /**
     * Deklarasi variable untuk number dengan type data integer
     * menyertakan type datanya secara eksplisit
     */
    val i: Int = 6

    /**
     * Deklarasi variable untuk mengkonversi nilai pada variable i
     * Konversi nilai dari integer ke byte dengan fungsi kotlin toByte()
     */
    val b1 = i.toByte() // 6

    /**
     * Deklarasi variable dengan type data byte dengan nilai yang berbeda dari sebelumnya
     */
    val b2: Byte = 1
    println(b2)

    /**
     * Menetapkan nilai byte ke jenis type data yang berbeda dengan nilai yang salah
     */
//    val i1: Int = b2 // => error: type mismatch: inferred type is Byte but Int was expected

//    val i2: String = b2 // => error: type mismatch: inferred type is Byte but String was expected

//    val i3: Double = b2 // => error: type mismatch: inferred type is Byte but Double was expected

    /**
     * Menetapkan nilai byte ke jenis type data yang berbeda dengan nilai yang benar
     * Convert type data
     */
    val i1: Int = b2.toInt() // => 1

    val i2: String = b2.toString() // => 1

    val i3: Double = b2.toDouble() // => 1.0


    /**
     * Kotlin membantu untuk memudahkan keterbacaan konstanta numerik yang nilai nya panjang
     * Seperti menggunakan garis bawah
     */
    val oneMillion = 1_000_000
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0XFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010



}
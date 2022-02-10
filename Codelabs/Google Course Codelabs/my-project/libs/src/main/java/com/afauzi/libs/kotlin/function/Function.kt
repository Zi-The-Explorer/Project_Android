package com.afauzi.libs.kotlin.function

// Import package ini untuk mengakses seluruh paket termasuk random
import java.util.*

// Todo => Membuat beberapa fungsi

fun main() {
    feedTheFish() // => Mengakses feedTheFish()
}

/**
 * Membuat fuungsi untuk fishFood() berdasarkan hari
 */
fun fishFood(day: String): String {
    /**
     * Deklarasikan food dengan nilai kosong, yang nanti akan diubah pada saat steatment dalam when kondisi
     */
//    var food = ""
//    when (day) {
//        "Monday" -> food = "flakes"
//        "Tuesday" -> food = "pellets"
//        "Wednesday" -> food = "redworms"
//        "Thursday" -> food = "granules"
//        "Friday" -> food = "mosquitoes"
//        "Saturday" -> food = "lettuce"
//        "Sunday" -> food = "plankton"
//        else -> food = "Nothing"
//    }
//    return food

    /**
     * Versi Lebih ringkas
     */
    return when (day) {
        "Monday" -> "flakes"
        "Tuesday" -> "pellets"
        "Wednesday" -> "redworms"
        "Thursday" -> "granules"
        "Friday" -> "mosquitoes"
        "Saturday" -> "lettuce"
        "Sunday" -> "plankton"
        else -> "Nothing"
    }
}

/**
 * Membuat sebuah fungsi yang akan dicetak
 */
fun feedTheFish() {
    /**
     * Deklarasi variable untuk mengakses fungsi randomDay() yang sudah dibuat
     */
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the the fish eat $food")
    println("Change Water: ${shouldChangeWater(day)}")
}

/**
 * Membuat fungsi random hari, yang akan digunakan didalam fungsi feedTheFish()
 */
fun randomDay(): String {
    /**
     * Deklarasi variable yang menentukan nilai nya adalah sebuah daftar hari
     */
    val week = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(week.size)] // Kembalikan untuk memanggil satu item index secara random
}

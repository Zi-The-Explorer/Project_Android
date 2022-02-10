package com.afauzi.libs.kotlin.function

/**
 * Fungsi utama untuk mencetak
 */
fun main() {
    swim() // => uses default speed
    swim("slow") // => Positional argument
    swim(speed = "turtle-like") // => named argument
}

/**
 * Fungsi yang akan membuat kecepatan ikan
 *
 * dan parameter speed memiliki nilai default yaitu "fast"
 */
fun swim(speed: String = "fast") {
    println("swimming $speed")
}

/*
Todo =>
 Catatan: Argumen tidak harus menggunakan nama parameter; Anda bisa meneruskan argumen dalam urutan yang ditentukan.
 Tetapi dengan nilai default, ini bisa sedikit membingungkan,
 jadi praktik terbaik adalah menempatkan parameter tanpa default terlebih dahulu,
 dan parameter dengan default setelahnya.
 */

//fun shouldChangeWater(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
//    return when  {
//        temperature > 30 -> true
//        dirty > 30 -> true
//        day == "Sunday" -> true
//        else -> false
//    }
//}

/*
    Todo => Fungsi fungsi ringkas ini untuk menguji kondisi
 */
fun isToHoot(temperature: Int) = temperature > 30
fun isDirty(dirty: Int) = dirty > 30
fun isDay(day: String) = day == "Sunday"

fun shouldChangeWater(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when  {
        isToHoot(temperature) -> true
        isDirty(dirty) -> true
        isDay(day) -> true
        else -> false
    }
}

/*
Todo =>
 Nilai default untuk parameter tidak harus berupa nilai.
 Ini bisa menjadi fungsi lain, seperti yang ditunjukkan pada contoh parsial berikut:
 */

//fun shouldChangeWater (day: String, temperature: Int = 22, dirty: Int = getDirtySensorReading()): Boolean {
//    ...
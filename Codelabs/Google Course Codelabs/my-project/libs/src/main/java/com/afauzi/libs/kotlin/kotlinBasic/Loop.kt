package com.afauzi.libs.kotlin.kotlinBasic

fun main() {

    /**
     * Menggunakan for loop untuk mencetak seluruh data dalam array
     */
    val school = arrayOf("Shark", "Salmon", "Minnow")
    for (element in school) {
        print(element + " ")
    }

    println()

    /**
     * for loop di kotlin juga dapat mengakses index array nya bersamaan bersama element nya
     */
    for ((index, element) in school.withIndex()) {
        println("$index => $element")
    }

    /**
     * Menggunakan range dan langkah nya menggunakan for
     */
    for (i in 1..5) print("$i")
    println()
    for (i in 5 downTo 1) print("$i")
    println()
    for (i in 1..10 step 2) print("$i")
    println()
    for (i in 'a'..'j') print("$i")
    println()

    /**
     * Kotlin Memiliki for loop
     * while loop
     * do..while
     * ++ dan -- operator
     * repeat loop
     */
    var bubles = 0
    while (bubles < 50) {
        bubles++
    }
    println("$bubles buble in the water\n")

    do {
        bubles--
    }while (bubles > 50)
    println("$bubles buble in the water\n")

    repeat(2) {
        println("$it A fish a swimming")
    }

}
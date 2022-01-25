package `array&list&loop`

fun main() {
    /**
     * Menggunakan for loop untuk beralih melalui array dan dapat mencetak elemen array
     */
    val school = arrayOf("shark", "salmon", "minnow")
    for (element in school) {
        println(element)
    }
    println()


    // Todo: Di Kotlin, dapat mengulang elemen dan index secara bersamaan
    for ((index, element) in school.withIndex()) {
        println("Element $index is $element")
    }

    // Todo: Mengakses Range menggunakan for loop
    // Todo: Melangkah kedepan
    for (i in 1..10) {
        println(i)
    }
    println()

    // Todo: Melangkah kebelakang dari 5 ke 1
    for (i in 5 downTo 1) {
        println(i)
    }
    println()

    // Todo: Melangkah ke depan dari 1 ke 10 dengan step 2 melompati 2 angka
    for (i in 1..10 step 2) {
        println(i)
    }
    println()

    /**
     * Kotlin memiliki loop berupa while, do..while, dan for
     * increment(++) dan decrement(--)
     */
    var bubbles = 0
    while (bubbles < 50) {
        bubbles++
    }
    println("$bubbles bubbles in the water\n")
    println()

    do {
        bubbles--
    } while (bubbles > 50)
    println("$bubbles bubbles in the water\n")
    println()

    repeat(2) {
        println("A fish a swimming")
    }

}
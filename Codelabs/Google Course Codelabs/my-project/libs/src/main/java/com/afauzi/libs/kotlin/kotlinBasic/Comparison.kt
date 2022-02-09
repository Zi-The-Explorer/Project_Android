package com.afauzi.libs.kotlin.kotlinBasic

fun main() {

//    Todo => bahasa lainnya, Kotlin memiliki boolean dan operator boolean seperti kurang dari,
//              sama dengan, lebih besar dari, dan seterusnya ( <, ==, >, !=, <=, >=).


    /**
     * Pernyataan if/else
     */
    val numberOfFish = 50
    val numberOfPlants = 23
    if (numberOfFish > numberOfPlants) {
        println("Good ratio!") // => output
    } else {
        println("Unhealth ratio")
    }

    /**
     * Pernyataan if dengan range
     */
    val fish = 50
    if (fish in 1..100) {
        println("$fish in range")
    }

    /**
     * Untuk penggunaan banyak kasus bisa gunakan else if dan juga operator logika Or (||) dan (&&)
     */

    if (numberOfFish == 0) {
        println("Empty tank")
    } else if (numberOfFish < 40) {
        println("Got Fish!")
    } else {
        println("That's a lot of fish!") // => Output
    }






}
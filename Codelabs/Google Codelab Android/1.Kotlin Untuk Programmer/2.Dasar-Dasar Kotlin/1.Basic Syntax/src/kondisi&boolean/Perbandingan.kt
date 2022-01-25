package `kondisi&boolean`

fun main() {
    val numberOfFish = 50
    val numberOfPlants = 23
    if (numberOfFish > numberOfPlants) {
        println("Good ratio!")
    } else {
        println("Unhealthy ratio!")
    }
    println()

    val fish = 50
    if (fish in 1..100) {
        println(fish)
    }
    println()

    if (numberOfFish == 0) {
        println("Empty tank!")
    } else if (numberOfFish < 40) {
        println("Got fish!")
    } else {
        println("That's a lot of fish!")
    }
    println()

    when(numberOfFish) {
        0 -> println("Empty tank!")
        in 1..40 -> println("Got fish!")
        else -> println("That's a lot of fish!")
    }
}
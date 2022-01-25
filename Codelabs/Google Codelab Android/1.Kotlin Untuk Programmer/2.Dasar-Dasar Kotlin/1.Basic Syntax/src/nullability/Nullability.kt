package nullability


fun main() {
//    val rocks: Int = null
//    ⇒ error: null can not be a value of a non-null type Int

    /**
     * Menggunakan tanda ? setelah tipe data
     * menunjukan bahwa suatu variable bisa null
     */
    var marbles : Int? = null // OK

    /**
     * Memeriksa apakah suatu variable tidak null
     */
    var fishFoodTreats = 6
    if (fishFoodTreats != null) {
        fishFoodTreats = fishFoodTreats?.dec()
        println(fishFoodTreats)
    }
    println()

    /**
     * Mengecek null dengan elvis operator
     * Jika nilai suatu variable null, makan elvis operator(?:) akan di eksekusi
     * jika nilai tidak null maka nilai variable akan di eksekusi
     */
    fishFoodTreats = fishFoodTreats.dec() ?: 0
    println(fishFoodTreats)
    println()

    /**
     * Keamanan null (nullpointer exception)
     * null pointer exception adalah kesalahan yang muncul ketika kita mengakses variable yang null
     * Menggunakan operator pernyataan not null !!
     */
    val fishFoodTreats2 = fishFoodTreats!!.dec()
    println(fishFoodTreats2)
}
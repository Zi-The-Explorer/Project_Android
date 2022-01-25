package operator

fun main() {
    val i = 6
    val b1 = i.toByte()
    println(b1)
    println()

    val b2: Byte = 1 // OK, literals are checked statically
    println(b2)
    println()


//    val i1: Int = b2
    // ⇒ error: type mismatch: inferred type is Byte but Int was expected

//    val i2: String = b2
    // ⇒ error: type mismatch: inferred type is Byte but String was expected

//    val i3: Double = b2
    // ⇒ error: type mismatch: inferred type is Byte but Double was expected

    val i4: Int = b2.toInt() // OK!
    println(i4)

    val i5: String = b2.toString()
    println(i5)

    val i6: Double = b2.toDouble()
    println(i6)
    println()

    /**
     * Membuat Konstanta
     */
    val oneMillion = 1_000_000
    val socialSecurityNumber = 987_654_321L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010

    /**
     * Mendefinisikan variabel val dan var untuk menertapkan nilai baru
     */
    var fish = 1
    fish = 2

    val aquarium = 1
//    aquarium = 2
//    ⇒ error: val cannot be reassigned

    /**
     * Menentukan beberapa variable dan menentukan tipe data nya secara eksplisit
     */
    val fish1: Int = 12
    val lakes: Double = 3.14
}
package com.afauzi.libs.kotlin.function

fun main() {
// Todo =>
//  Seperti fungsi bernama, lambdas dapat memiliki parameter.
//  Untuk lambda, parameter (dan tipenya, jika diperlukan) berada di sebelah kiri dari apa yang disebut fungsi arrow ->.
//  Kode yang akan dieksekusi berada di sebelah kanan panah fungsi.
//  Setelah lambda ditetapkan ke variabel, Anda dapat memanggilnya seperti fungsi.

    /**
     * Deklarasi Sebuah nilai untuk di eksekusi disebuah lambdas
     */
    var dirtyLevel = 20
    val waterFilter = { dirty: Int ->
        dirty / 2
    }
    println(waterFilter(dirtyLevel)) // => Untuk mengakses sebuah lambdas, Sama sperti hal nya mengakses fungsi biasa

    /**
     * Sintaks kotlin untuk tipe fungsi terkait erat dengan sintaksnya untuk lambda.
     * Gunakan sintaks ini untuk mendeklarasikan variable yang memiliki fungsi dengan bersih
     */
    var waterFilter2: (Int) -> Int = { dirty -> dirty / 2 }
    println(waterFilter2(20))


    /**
     * Untuk Memanggil fungsi updateDirty()
     * Berikan integer dan fungsi
     */
    val waterFilter3: (Int) -> Int = {dirty -> dirty / 2}
    println((updateDirty(30, waterFilter3)))

    println(updateDirty(15, ::increasDirty))

//    Todo =>
//     Kotlin lebih suka bahwa parameter apa pun yang mengambil fungsi adalah parameter terakhir.
//     Saat bekerja dengan fungsi tingkat tinggi, Kotlin memiliki sintaks khusus, yang disebut sintaks panggilan parameter terakhir , yang memungkinkan Anda membuat kode lebih ringkas.
//     Dalam hal ini, Anda dapat meneruskan lambda untuk parameter fungsi, tetapi Anda tidak perlu meletakkan lambda di dalam tanda kurung.

    var dirtyLevel1 = 19
    dirtyLevel1 = updateDirty(dirtyLevel1) {dirtyLev -> dirtyLev + 23}
    println(dirtyLevel1)
}

// Todo =>
//  Sejauh ini, contoh untuk lambda sebagian besar terlihat seperti fungsi.
//  Kekuatan sebenarnya dari lambdas adalah menggunakannya untuk membuat fungsi tingkat tinggi,
//  di mana argumen untuk satu fungsi adalah fungsi lain.

/**
Tulis Fungsi tingkat tinggi.
Berikut adalah contoh dasar, fungsi yang membutuhkan dua argumen.
Argumen pertama adalah bilangan bulat.
Argumen kedua adalah fungsi yang mengambil bilangan bulat dan mengembalikan bilangan bulat.
 */
fun updateDirty(dirty: Int, operation: (Int) -> Int): Int {
    return operation(dirty) // => Body Pada function memanggil / mengembalikan argumen kedua dan meneruskan argumen pertama kedalamnya
}

// Todo =>
//  Fungsi yang Anda lewati tidak harus berupa lambda; itu bisa menjadi fungsi bernama biasa sebagai gantinya.
//  Untuk menentukan argumen sebagai fungsi reguler, gunakan ::operator.
//  Dengan cara ini Kotlin tahu bahwa Anda meneruskan referensi fungsi sebagai argumen, bukan mencoba memanggil fungsi.

/**
 * Lewati fungsi bernama biasa ke updateDirty
 */
fun increasDirty(start: Int) = start + 1
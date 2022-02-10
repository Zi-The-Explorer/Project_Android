package com.afauzi.libs.kotlin.function

fun main() {
    filter('a')
    filterLazy('p')
    lazyMap('a')
}

fun filter(searchChar: Char) {
    /**
     * Deklarasi varible untuk daftar yang akan di filter
     */
    val decoration = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

    /*
        Kode ini akan mencetak dekorasi yang hanya dimualai dengan huruf 'p' pada karakter pertama dari masing masing item
        Kode untuk kondisi filter dalam kurung kurawal {}, dan it merujuk ke setiap item variable decoration
        Kode dibawah ini artinya cari item yang karakter pertama nya berawalan 'p'
        [0] => karakter pertama index item
     */
    val eager = decoration.filter { it[0] == searchChar }
    println("Eager: $eager")
}

fun filterLazy(searchChar: Char) {

    /**
     * Deklarasi varible untuk daftar yang akan di filter
     */
    val decoration = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")


    /**
     * Evaluasi filter menggunakan a sequence with asSequence()
     * Tetapkan urutan ke variable yang disebut filtered dan cetak
     */
    val filtered = decoration.asSequence().filter { it[0] == searchChar }
    println("filtered: $filtered")

//    Todo =>
//     Saat Anda mengembalikan hasil filter sebagai a Sequence,
//     filteredvariabel tidak akan menyimpan daftar baru—ini akan menampung a Sequence dari elemen daftar dan pengetahuan tentang filter untuk diterapkan ke elemen tersebut.
//     Setiap kali Anda mengakses elemen Sequence, filter diterapkan, dan hasilnya dikembalikan kepada Anda.
//     dan hanya akan mengembalikan sebuah pemberitahuan memory sebuah sequence yang tidak dapat dibaca

    /**
     * Memaksa evaluasi urutan dengan mengubah nya menjadi List dengan toList()
     */
    val newList = filtered.toList()
    println("New list: $newList")
}

fun lazyMap(searchChar: Char) {
//    Todo =>
//     Untuk memvisualisasikan apa yang terjadi dengan Sequence evaluasi malas, gunakan map() fungsi.
//     Fungsi map()melakukan transformasi sederhana pada setiap elemen dalam urutan.

    /**
     * Deklarasi varible untuk daftar yang akan di filter
     */
    val decoration = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")


    /**
     * dengan decoration daftar yang sama seperti diatas,
     * buat transformasi dengan map(), itu tidak melakukan apa apa, dan cukup kembalikan elemen yang diteruskan
     *
     * Tambahkan println() untuk menunjukan setiap kali elemen diakses, dan tetapkan urutan ke variable yang disebut lazyMap
     */
    val lazyMap = decoration.asSequence().map {
        println("access: $it")
        it
    }
    println("lazy: $lazyMap") // => Output tidak terbaca, hanya status memory
    println("-----------")
    println("first: ${lazyMap.first()}") // => Output Mengakses item pertama decoration
    println("___________")
    println("all: ${lazyMap.toList()}") // => Mengakses seluruh item decoration

    /**
     * Membuat sequence baru menggunakan filter asli sebelum mendaftar map
     */
    val lazyMap2 = decoration.asSequence().filter { it[0] == searchChar }.map {
        println("Access $it")
        it
    }
    println("___________")
    println("filtered: ${lazyMap2.toList()}")


}
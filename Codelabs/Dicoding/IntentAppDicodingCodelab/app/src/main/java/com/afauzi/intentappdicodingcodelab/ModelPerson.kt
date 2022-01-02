package com.afauzi.intentappdicodingcodelab

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * data class berfungsi untuk menyimpan model data suatu obyek
 */

/**
 * menciptakan sebuah obyek Person bernama ModelPerson yang mana kelas tersebut adalah Parcelable.
 * Kita atur semua data sesuai dengan propertinya.
 * Kemudian kita akan mengirimkan obyek tersebut ke MoveWithObjectActivity melalui sebuah intent
 */
@Parcelize
data class ModelPerson(
    val name: String?,
    val age: Int?,
    val email: String?,
    val city: String?
) : Parcelable
// Data class akan membantu kita saat aplikasi semakin kompleks.
// Contohnya, data class bisa kita gunakan untuk melakukan koneksi ke server untuk request API atau akses ke database lokal dengan SQLite.


// Seperti yang diketahui bahwa di dalam Intent kita dapat menyisipkan data dengan tipe-tipe tertentu seperti string, int, double pada Intent.
// Tetapi tidak dengan tipe kompleks seperti objek, ArrayList, dll. Di sinilah peran besar Parcelable.
// Parcelable adalah suatu interface yang memungkinkan kita melakukan pengiriman objek dari suatu activity ke activity lain.
// Obyek yang di implementasikan dengan parcelable akan memudahkan Anda dalam mengirimkan data dari satu activity ke activity lainnya.
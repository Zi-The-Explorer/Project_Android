package com.afauzi.intentappdicodingcodelab

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult : TextView

    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Deklarasi moveActivivty
         */
        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        /**
         * Deklarasi Move Activity With Data
         */
        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        /**
         * Deklarasi Move Activity With Object
         */
        val btnMoveActivityWithObject: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveActivityWithObject.setOnClickListener(this)

        /**
         * Deklarasi Intent Implisit DialPhone
         */
        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        /**
         *
         */
        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_move_activity -> {

                /**
                 * Membuat suatu object intent dengan cara seperti dibawah dengan memberikan context this / this@MAinActivity
                 * dan clas activity tujuan nya MoveActivity::class.java constructor kelas intent
                 */
                val moveIntent = Intent(this, MoveActivity::class.java)

                /**
                 * Metode StartActivity(MoveIntent) akan menjalankan activity baru tanpa membawa data
                 * Obyek intent yang di inputkan adalah obyek moveIntent yang ketika kode dijalankan maka akan membuka MoveActivity
                 */
                startActivity(moveIntent)
            }

            /**
             * Perbedaan mendasar antara memindahkan Activity dengan membawa data atau tidak,
             * adalah dengan menempatkan data ke obyek Intent pada baris ini.
             */
            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this, MoveWithDataActivity::class.java)

                /**
                 * Kita memanfaatkan metode putEXtra() untuk mengirimkan data bersamaan dengan object intent
                 * sedangkan metode putExtra() itu sendiri merupakan metode yang menampung pasangan key-value dan memiliki beberapa pilihan tipe input seperti berikut
                 * dan hampir semua tipe data didukung oleh     putExtra()
                 */
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Akhmad Fauzi")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 24)

                /**
                 * Name : dimana EXTRA_NAME adalah variable static bertipe data string dan bernilai extra_name pada MoveWithDataActivity
                 * Penentuan nilai untuk key parameter untuk intent adalah bebas, rekomendasi yang seharusnya adalah merekomendasikan format terbaik yang biasa diimplementasikan
                 *
                 * Value : Akhmad Fauzi dengan tipe data String
                 */


                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = ModelPerson(name = "Akhmad Fauzi", age = 24, email = "akhmadfauzi189@gmail.com", city = "Bekasi")

                val moveWithObjectIntent = Intent(this, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "082112966360"

                /**
                 * Pada bagian Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber)),
                 * kita menggunakan inputan Intent(ACTION, Uri) pada konstruktor sewaktu menciptakan obyek Intent di mana:
                     Action           : Intent.ACTION_DIAL
                     Uri              : Uri.parse("tel:"+phoneNumber)
                 */
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {

                /**
                 * Pada baris ini kita akan menjalankan sebuah activity melalui intent untuk nilai balik ke activity yang menjalankan dimana nilai REQUEST_CODE adalah 110
                 * Penentuan nilai 110 itu dibebaskan dan kalau bisa disesuaikan dengan kebutuhan pengembang aplikasi
                 */
                val moveForResultIntent = Intent(this, MoveForResultActivity::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }

        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /**
         * Di sinilah MainActivity akan merespon terhadap nilai balik yang dikirimkan oleh MoveForResultActivity.
         * Pada baris 4 di atas, dilakukan perbandingan apakah requestCode sama dengan yang dikirimkan oleh MainActivity.
         */
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {

                /**
                 *  periksa apakah nilai resultCode sama yang dikirim oleh MoveForResultActivity.
                 *  Bila iya, maka data radiobutton yang dipilih akan ditampilkan di textview tvResult.
                 */
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tvResult.text = "Hasil : $selectedValue"
            }
        }
    }

    /**
     * context adalah sebuah kelas yang digunakan untuk mengakses resource dari activity tersebut.
     * Nanti nya kita membutuhkan banyak context seperti saat mengambil data dari resource, mengakses System Service,
     * Mendapatkan Application info dan lain sebagainya
     */

    /**
     * Kombinasi antara requestCode dan resultCode akan sangat berguna ketika kita ingin membuat suatu activity yang dapat merespon banyak skenario.
     * Perhatikan contoh kode di bawah ini, di mana terjadi banyak skenario yang bisa terjadi.
     */

    /**
     * Pada realita sehari-hari, konsep yang sudah kita pelajari akan bersinggungan dengan aplikasi native lainnya.
     * Misalnya, jika aplikasi kita membutuhkan gambar yang diambil dari kamera atau gallery photo.
     * Tentu kita mengharapkan nilai balik berupa alamat foto yang dapat diterima oleh activity yang menjalankan.
     * Begitu juga jika kita membutuhkan data kontak yang berasal dari aplikasi phonebook bawaan peranti Android.
     */
}
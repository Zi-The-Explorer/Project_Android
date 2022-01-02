package com.afauzi.intentappdicodingcodelab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class MoveForResultActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnChoose: Button
    private lateinit var rgNumber: RadioGroup

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btnChoose = findViewById(R.id.btn_choose)
        rgNumber = findViewById(R.id.rg_number)

        btnChoose.setOnClickListener(this)

    }

    /**
     * Kemudian pada MoveForResultActivity halaman ini kita memilih satu angka yang kita suka, sebagai contoh angka 150
     * kemudian tekanlah tombol 'pilih'. Maka baris kode dibawah ini akan dijalankan
     */
    override fun onClick(v: View?) {

        /**
         *  Kode ini berfungsi untuk melakukan validasi nilai dari obyek RadioButton yang dipilih
         *  Bila ada nilai radiobutton, maka proses selanjutnya adalah menentukan obyek RadioButton mana yang diklik berdasarkan nilai 
         */
        if (v?.id == R.id.btn_choose) {
            if (rgNumber.checkedRadioButtonId > 0) {
                var value = 0
                when(rgNumber.checkedRadioButtonId) {
                    R.id.rb_50 -> value = 50
                    R.id.rb_100 -> value = 100
                    R.id.rb_150 -> value = 150
                    R.id.rb_200 -> value = 200
                }

                /**
                 * Pertama membuat Sebuah intent tanpa ada inputan apapun di konstruktor nya
                 * Kemudian kita meletakkan variabel value ke dalam metode putExtra(Key, Value) dengan EXTRA_SELECTED_VALUE bertipekan static string dan bernilai “extra_selected_value”.
                 * Kemudian kita jadikan obyek resultIntent yang telah dibuat sebelumnya menjadi parameter dari setResult(RESULT_CODE, Intent).
                 * Setelah itu, kita panggil method finish() untuk menutup MoveForResultActivity.
                 * Ketika MoveForResultActivity telah tertutup sempurna, maka metode onActivityResult() pada MainActivity akan dijalankan.
                 */
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                setResult(RESULT_CODE, resultIntent)
                finish()

            }
        }
    }
}
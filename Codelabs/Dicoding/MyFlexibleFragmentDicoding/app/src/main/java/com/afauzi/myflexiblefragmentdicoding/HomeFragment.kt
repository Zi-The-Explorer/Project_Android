package com.afauzi.myflexiblefragmentdicoding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment(), View.OnClickListener {

    /**
     * metode onCreateView() di mana layout interface didefinisikan dan ditransformasikan dari layout berupa file xml ke dalam objek view dengan menggunakan metode inflate().
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /**
     * metode onViewCreated() yang akan bekerja setelah metode onCreateView().
     * Di sini kita bisa gunakan untuk inisialisasi view, dan juga mengatur action-nya (set listener).
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Catatan:
         * Perlu diperhatikan untuk pemanggilan findViewById() di sini tidak dapat langsung dilakukan seperti di Activity.
         * Anda perlu menambahkan variabel view terlebih dulu di depannya sehingga menjadi view.findViewById().
         */
        val btnCategory: Button = view.findViewById(R.id.btn_category)
        btnCategory.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }

    /**
     * Inflater.inflate() merupakan objek dari LayoutInflater yang berfungsi untuk mengubah layout xml ke dalam bentuk objek viewgroup atau widget melalui pemanggilan metode inflate().
     * Sama seperti setContentView pada Activity, fungsi inflate di sini yaitu untuk menampilkan layout dari Fragment, di mana layout yang ditampilkan di sini yaitu fragment_home.
     */

}
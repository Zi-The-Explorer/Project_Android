package com.afauzi.implicit_intens

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.app.ShareCompat

@Suppress("SENSELESS_COMPARISON")
class MainActivity : AppCompatActivity() {
    // Deklarasi variabel untuk menampung Edit Text
    private lateinit var editTextOpenUrl: EditText
    private lateinit var editTextOpenLocation: EditText
    private lateinit var editTextShareText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Dalam metode onCreate(),
        // gunakan findViewByID() untuk mendapatkan referensi tentang instance EditText dan menetapkannya ke variabel privat tersebut:
        editTextOpenUrl = findViewById(R.id.edit_text_uri)
        editTextOpenLocation = findViewById(R.id.edit_text_open_location)
        editTextShareText = findViewById(R.id.edit_text_share)
    }

    // Method untuk membuka website
    fun openWebsite(view: View) {
        // mendapatkan / mengambil nilai string dari edit text
        val url: String = editTextOpenUrl.text.toString()

        //  Enkode dan parse string ke dalam objek Uri
        val webpage: Uri = Uri.parse(url)

        // Buat Intent baru dengan Intent.ACTION_VIEW sebagai tindakan dan URI Sebagai data
        // menyertakan ACTION_VIEW (untuk melihat data yang diberikan),
        // ACTION_EDIT (untuk mengedit data yang diberikan),
        // atau ACTION_DIAL (untuk menghubungi nomor telepon).
        // Dalam hal ini, tindakan adalah ACTION_VIEW karena kita ingin membuka dan melihat laman web yang ditetapkan oleh URI dalam variabel laman web. 8
        val intent: Intent = Intent(Intent.ACTION_VIEW, webpage)

        // Gunakan resolveActivity() dan pengelola paket Android untuk menemukan aktivitas yang dapat menangani intent implisit.
        // Periksa kondisi untuk memastikan permintaan berhasil diatasi.
        if (intent != null) {
            // Dalam pernyataan if, panggil startActivity() untuk mengirimkan intent.
            startActivity(intent)

            // Tambahkan blok else untuk mencetak pesan pada Log jika intent tidak bisa di atasi
        } else {
            Log.d("ImplicitIntents: ", " Tidak bisa di atasi!")
        }

    }

    // methode untuk membuka location
    fun openLocation(view: View) {
        // mendapatkan / mengambil nilai string dari edit text
        val location: String = editTextOpenLocation.text.toString()
        // Parse string itu ke dalam objek Uri dengan kueri penelusuran geo
        val uriAddrees: Uri = Uri.parse("geo:0,0?q=$location")

        // Buat intent baru dengan Intent.ACTION_VIEW sebagai tindakan dan loc sebagai datanya.
        val inten: Intent = Intent(Intent.ACTION_VIEW, uriAddrees)

        // Pecahkan intent dan periksa untuk memastikan intent berhasil diatasi. Jika demikian, startActivity(),
        // jika tidak catat log pesan kesalahan.
        if (inten !== null) {
            startActivity(inten)
        } else {
            Log.d("Open Location", "Tidak Bisa Di atasi!")
        }
    }

    // Untuk Share Sebuah text / link bertype string
    fun shareText(view: View) {
        // Mendapatkan nilai string pada edit text share text
        val textShare: String = editTextShareText.text.toString()

        // Definisikan tipe mime teks untuk dibagikan
        val mimeType: String = "text/plain"

        // Panggil ShareCompat.IntentBuilder dengan metode ini
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this text with: ")
            .setText(textShare)
            .startChooser()
    }
}
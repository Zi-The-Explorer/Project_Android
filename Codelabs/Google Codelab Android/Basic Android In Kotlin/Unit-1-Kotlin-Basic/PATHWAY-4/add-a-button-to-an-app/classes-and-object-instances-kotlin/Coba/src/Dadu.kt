// Memberikan constructor jumlahSisiDadu pada class Dadu() agar nilai nya nanti dapat diisi secara variatif / berbeda beda
class Dadu(val jumlahSisiDadu: Int, val colors: String) {

    // Function / Methode untuk membuat lemparan dadu secara acak
    fun roll(): Int{
        return (1..jumlahSisiDadu).random()
    }

    fun colors(): String {
        return colors
    }

    /**
     * Ringkasan :
     * Untuk menghasilkan angka acak, Gunakan fungsi random() pada type data IntRange
     *
     * class itu seperti object blue print.
     * Template yang dapat memiliki property dan prilaku yang di terapkan pada variable dan fungsi
     *
     * Instance class menyatakan sebuah object, dan seringkali berupa object fisik, seperti dadu.
     * Anda dapat memanggil tindakan pada object dan mengubah atribute nya
     *
     * Anda dapat memberikan nilai ke class saat membuat instance.
     * Misalnya: class Dadu(val jumlahSisiDadu: Int) lalu buat instance dengan Dadu(6)
     *
     * Fungsi dapat menmpilkan sesuatu.
     * Tentukan jenis data yang akan ditampilkan dalam definisi fungsi, dan gunakan pernyataan return dalam fungsi
     * untuk menampilkan sesuatu.
     * Misalnya: fun example(): Int { return 5 }
     */
}
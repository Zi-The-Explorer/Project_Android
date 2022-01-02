fun main() {

    // Instance / Buat object untuk menginisialisasikan sebagai instance dari class Dadu()
    val daduPertama = Dadu(6, "RED")
    println("""
        Jumlah sisi pada dadu pertama: ${daduPertama.jumlahSisiDadu}
        Warna pada dadu pertama: ${daduPertama.colors}
        dadu berhenti berputar di angka: ${daduPertama.roll()}
    """.trimIndent())

    println()

    val daduKedua = Dadu(20, "BLUE")
    println("""
        Jumlah sisi pada dadu kedua: ${daduKedua.jumlahSisiDadu}
        Warna pada dadu kedua: ${daduKedua.colors}
        dadu berhenti berputar di angka: ${daduKedua.roll()}
    """.trimIndent())
}
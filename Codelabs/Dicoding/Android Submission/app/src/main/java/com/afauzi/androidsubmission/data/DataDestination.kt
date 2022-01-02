package com.afauzi.androidsubmission.data

import com.afauzi.androidsubmission.R
import com.afauzi.androidsubmission.model.ModelDestination

object DataDestination {

    // Data Nama
    private val dataNameDestination = arrayOf(
        // Island Destination
        "Pulau Kei", // 0
        "Pulau Komodo",    // 1
        "Pulau Banda Neira", // 2
        "Pulau Karimun Jawa",   // 3
        "Pulau Raja Ampat",// 4

        // Mountain Destination
        "Gunung Bromo", // 5
        "Gunung Merbabu",   // 6
        "Gunung Prau",      // 7
        "Gunung Rinjani",    // 8
        "Gunung Semeru"      // 9
    )

    // Data Lokasi
    private val dataLocationDestination = arrayOf(
        "Maluku",  // 0
        "Nusa Tenggara Timur",               // 1
        "Maluku",              // 2
        "Jawa Tengah",              // 3
        "Papua Barat",              // 4
        "Jawa Timur",                // 5
        "Jawatengah",           // 6
        "Jawatengah",           // 7
        "Nusa Tenggara Barat",           // 8
        "Jawa Timur"                 // 9
    )

    // Data Gambar
    private val dataImageDestination = arrayOf(
        R.drawable.keiisland,
        R.drawable.komodoisland,
        R.drawable.bandaneira,
        R.drawable.karimunjawa,
        R.drawable.rajaampat,
        R.drawable.bromo,
        R.drawable.merbabu,
        R.drawable.prau,
        R.drawable.rinjani,
        R.drawable.semeru
    )

    // Detail Destinasi
    private val dataDetailDestinastion = arrayOf(
        "Kepulauan Kei masuk dalam wilayah Provinsi Maluku yang memiliki luas pulau 1438 km2. Puluhan gugusan pulau-pulau kecil terbentang indah di kepulauan Kei. Keunikan wisata alamnya yang kurang terekspos itulah sehingga muncul sebutan Surga Tersembunyi bagi masyarakat Kei.",
        "Pulau Komodo menjadi salah satu tempat wisata di NTT yang memiliki keindahan alam dan air laut yang bagus. Disini kalian bisa menemukan hewan komodo dan menyaksikan kegiatan harian hewan tersebut.   Selain itu, kamu juga bisa berkunjung ke Pantainya untuk menikmati putihnya pasir dan jernihnya air laut pulau Komodo.",
        "Ketika Mama mengunjungi Pulau Banda Neira, Mama akan melihat pemandangan yang begitu megah. Pemandangan antara bentangan laut yang luas dan megahnya gunung yang menjulang membuat pesona Banda Neira sulit untuk dipungkiri.",
        "Karimunjawa adalah kepulauan di Laut Jawa yang termasuk dalam Kabupaten Jepara, Jawa Tengah. Dengan luas daratan ±1.500 hektare dan perairan ±110.000 hektare, Karimunjawa kini dikembangkan menjadi pesona wisata Taman Laut yang mulai banyak digemari wisatawan lokal maupun mancanegara. Karimunjawa memiliki beberapa julukan seperti Pulau Liburan, The Paradise of Java, atau Caribbaen van Java.",
        "Raja Ampat adalah kepulauan yang terdiri dari banyak sekali pulau karang dan tersebar luas di seluruh wilayahnya. Namun demikian, Raja Ampat memiliki 4 pulau utama yang paling besar, yaitu Pulau Waigeo, Pulau Batanta, Pulau Salawati, dan Pulau Misool.",
        "Gunung Bromo memiliki keindahan alam yang menawan. Pesonanya membuat para pengunjung selalu takjub dan rindu untuk datang lagi.Bromo adalah gunung berapi yang masih aktif dan menjadi objek wisata kebanggaan warga di Jawa Timur.",
        "gunung ini menawarkan keindahan yang sulit untuk dilupakan. Sensasi pendakian yang menantang dengan pemandangan yang mengagumkan menjadi oase tersendiri bagi para pendaki, penikmat ketinggian yang akrab dengan udara dingin khas pegunungan. Bertetangga dengan Merapi, gunung ini tidak kalah dalam menawarkan keindahannya yang tiada tara. Tak heran, oleh sebagian pendaki ia dijuluki “Little Rinjani.” Gunung Merbabu, namanya.",
        "Dari puncak Gunung Prau pendaki dapat menikmati pemandangan serba cantik di antaranya hamparan keindahan bukit teletubbies dan bunga yang sangat menawan. Tak hanya itu, kita juga dapat menyaksikan keindahan jajaran Gunung Sumbing, Sindoro, Merapi, Merbabu, dan Slamet.",
        "anda pecinta alam dan penikmat gunung? Belum lengkap rasanya jika Anda belum merasakan sensasi mendaki di gunung yang terkenal sangat cantik akan pesona alamnya ini. Dengan ketinggian 3.726 mdpl dan terletak di utara Pulau Lombok, Gunung Rinjani merupakan gunung berapi kedua tertinggi di Indonesia.",
        "Sejak dulu, pesona gunung Semeru yang terletak di dua kabupaten, Lumajang dan Malang, seolah gak pernah bosan menjerat setiap hati penikmat alam. Ada daya pikat yang dipunya sehingga pendaki dari seluruh penjuru Nusantara hingga dunia datang silih berganti membuktikan sendiri kebenaran tersebut."


    )

    val listData: ArrayList<ModelDestination> get() {
        val list = arrayListOf<ModelDestination>()
        for (position in dataNameDestination.indices) {
            val destination = ModelDestination()
            destination.nameDestination = dataNameDestination[position]
            destination.locationDestination = dataLocationDestination[position]
            destination.imageDestination = dataImageDestination[position]
            destination.detailDestination = dataDetailDestinastion[position]
            list.add(destination)
        }
        return list
    }



}
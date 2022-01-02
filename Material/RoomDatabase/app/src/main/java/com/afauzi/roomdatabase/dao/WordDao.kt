package com.afauzi.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afauzi.roomdatabase.model.Word
import kotlinx.coroutines.flow.Flow

/**
 * Anotasi Dao Mendefinisikan sebagai class DAO untuk Room
 */
@Dao

/**
 * Dao harus berupa interface atau class abstract
 */
interface WordDao {

    /**
     * @Query("SELECT * FROM word_table ORDER BY word ASC") : Kueri yang mehasilkan daftar kata yang diurutkan dalam urutan naik
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")

            /**
             * fun getAlphabetizedWords(): List<Word> : Metode untuk mendapatkan semua kata dan menghasilkan list Words
             */
    fun getAlphabetizedWords(): Flow<List<Word>>

    /**
     * Anotasi @Insert adalah anotasi metode DAO Khusus, sehingga tidak perlu menyediakan SQL apapun
     * Adapula anotasi @Delete dan @Update untuk menghapus dan memperbarui baris, tetapi tidak digunakan dalam aplikasi ini
     *
     * onConflict = OnConflictStrategy.IGNORE : akan mengabaikan kata baru jika sama persis dengan kata yang sudah ada dalam daftar
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    /**
     * Suspend fun insert (word:Word)
     * Mendeklarasikan fungsi penangguhan untuk menyisipkan satu kata
     */
    suspend fun insert(word: Word)

    /**
     * @Query ("DELETE FROM word_table") : Mengharuskan menyediakan kueri SQL sebagai parameter string ke anotasi, sehingga memungkinkan kueri baca yang kompleks dan operasi lainnya
     */
    @Query("DELETE FROM word_table")
    /**
     * Mendeklarasikan fungsi penangguhan untuk menghapus semua kata
     * Tidak ada anotasi praktis untuk menghapus beberapa entity, sehingga tindakan tersebut diberi anotasi dengan @Query generik
     */
    suspend fun deleteAll()

}
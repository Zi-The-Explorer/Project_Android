package com.afauzi.roomdatabase.repo

import androidx.annotation.WorkerThread
import com.afauzi.roomdatabase.dao.WordDao
import com.afauzi.roomdatabase.model.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}
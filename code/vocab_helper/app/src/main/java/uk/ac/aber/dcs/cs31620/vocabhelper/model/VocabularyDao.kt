package uk.ac.aber.dcs.cs31620.vocabhelper.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary_item")
    fun getAllVocabItems(): LiveData<List<VocabularyItem>>
}
package uk.ac.aber.dcs.cs31620.vocabhelper.model.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyItem

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary_item")
    fun getAllVocabItems(): LiveData<List<VocabularyItem>>
}
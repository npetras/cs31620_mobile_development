package uk.ac.aber.dcs.cs31620.vocabhelper.model.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyItem

@Dao
interface VocabularyDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertVocabItem(vocabItem: VocabularyItem)

    @Query("SELECT * FROM vocabulary_item")
    fun getAllVocabItems(): LiveData<List<VocabularyItem>>
}
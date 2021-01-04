package uk.ac.aber.dcs.cs31620.vocabhelper.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import uk.ac.aber.dcs.cs31620.vocabhelper.model.database.entity.VocabularyItem

@Dao
interface VocabularyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVocabItem(vocabularyItem: VocabularyItem)

    @Query("SELECT * FROM vocabulary_item")
    fun getAllVocabItems(): LiveData<List<VocabularyItem>>
}
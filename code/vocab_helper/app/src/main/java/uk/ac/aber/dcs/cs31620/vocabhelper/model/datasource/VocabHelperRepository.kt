package uk.ac.aber.dcs.cs31620.vocabhelper.model.datasource

import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyDao
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyItem
import javax.inject.Inject

class VocabHelperRepository @Inject constructor(
    private val vocabularyDao: VocabularyDao
) {
    fun getAllVocabItems(): LiveData<List<VocabularyItem>> {
        return vocabularyDao.getAllVocabItems()
    }
}
package uk.ac.aber.dcs.cs31620.vocabhelper.model.persistence

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyItem

class VocabHelperRepository (application: Application) {
    private val vocabularyDao = VocabHelperRoomDatabase.getInstance(application).vocabularyDao()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertVocabItem(vocabularyItem: VocabularyItem) {
        coroutineScope.launch(Dispatchers.IO) {
            vocabularyDao.insertVocabItem(vocabularyItem)
        }
    }

    fun getAllVocabItems(): LiveData<List<VocabularyItem>> {
        return vocabularyDao.getAllVocabItems()
    }
}
package uk.ac.aber.dcs.cs31620.vocabhelper.model.persistence

import android.app.Application
import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyItem
import javax.inject.Inject

class VocabHelperRepository (application: Application) {
    private val vocabularyDao = VocabHelperRoomDatabase.getInstance(application).vocabularyDao()

    fun getAllVocabItems(): LiveData<List<VocabularyItem>> {
        return vocabularyDao.getAllVocabItems()
    }
}
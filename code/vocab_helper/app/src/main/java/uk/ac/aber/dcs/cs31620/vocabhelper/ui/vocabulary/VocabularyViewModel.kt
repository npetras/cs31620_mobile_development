package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import uk.ac.aber.dcs.cs31620.vocabhelper.model.persistence.VocabHelperRepository

/**
 * View model for [VocabularyFragment]
 */
class VocabularyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VocabHelperRepository(application)
    var vocabularyItems = repository.getAllVocabItems()
        private set
}
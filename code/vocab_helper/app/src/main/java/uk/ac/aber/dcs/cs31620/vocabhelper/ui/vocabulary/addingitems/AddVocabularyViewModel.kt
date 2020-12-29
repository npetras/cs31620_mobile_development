package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary.addingitems

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import uk.ac.aber.dcs.cs31620.vocabhelper.model.database.VocabHelperRepository

class AddVocabularyViewModel(application: Application) : AndroidViewModel(application) {
    val repository = VocabHelperRepository(application)
}
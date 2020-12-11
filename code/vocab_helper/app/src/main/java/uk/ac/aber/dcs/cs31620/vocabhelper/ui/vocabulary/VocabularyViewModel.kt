package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import uk.ac.aber.dcs.cs31620.vocabhelper.model.datasource.VocabHelperRepository

class VocabularyViewModel @ViewModelInject constructor(
    vocabHelperRepository: VocabHelperRepository
) : ViewModel() {
    var vocabularyItems = vocabHelperRepository.getAllVocabItems()
        private set
}
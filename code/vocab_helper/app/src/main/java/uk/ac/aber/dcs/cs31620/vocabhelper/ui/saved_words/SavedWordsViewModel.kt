package uk.ac.aber.dcs.cs31620.vocabhelper.ui.saved_words

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedWordsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Saved Words Fragment"
    }
    val text: LiveData<String> = _text
}
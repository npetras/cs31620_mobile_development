package uk.ac.aber.dcs.cs31620.vocabhelper.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Quiz Fragment"
    }
    val text: LiveData<String> = _text
}
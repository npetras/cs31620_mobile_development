package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary.addingitems

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.ac.aber.dcs.cs31620.vocabhelper.R

class AddVocabularyFragment : Fragment() {

    private lateinit var viewModel: AddVocabularyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_vocabulary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddVocabularyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
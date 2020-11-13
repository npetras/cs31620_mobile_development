package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import uk.ac.aber.dcs.cs31620.vocabhelper.R

class VocabularyFragment : Fragment() {

    private lateinit var vocabularyViewModel: VocabularyViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        vocabularyViewModel =
                ViewModelProviders.of(this).get(VocabularyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vocabulary, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        vocabularyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
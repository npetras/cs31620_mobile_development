package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import uk.ac.aber.dcs.cs31620.vocabhelper.databinding.FragmentVocabularyBinding


class VocabularyFragment : Fragment() {

    private lateinit var vocabFragmentBinding: FragmentVocabularyBinding;
    private lateinit var vocabViewModel: VocabularyViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        vocabViewModel = ViewModelProvider(this).get(VocabularyViewModel::class.java)
        vocabFragmentBinding = FragmentVocabularyBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return vocabFragmentBinding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = vocabFragmentBinding.vocabularyList
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
            false)
        recyclerView.layoutManager = linearLayoutManager
    }
}
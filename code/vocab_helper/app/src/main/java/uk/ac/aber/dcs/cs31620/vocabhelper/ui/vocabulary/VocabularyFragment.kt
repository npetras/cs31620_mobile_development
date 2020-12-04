package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.vocabulary_item.view.*
import uk.ac.aber.dcs.cs31620.vocabhelper.R
import uk.ac.aber.dcs.cs31620.vocabhelper.databinding.FragmentVocabularyBinding
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyList


class VocabularyFragment : Fragment() {

    private lateinit var vocabFragmentBinding: FragmentVocabularyBinding;
    private lateinit var vocabViewModel: VocabularyViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        vocabViewModel = ViewModelProvider(this).get(VocabularyViewModel::class.java)
        vocabFragmentBinding = FragmentVocabularyBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return vocabFragmentBinding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = vocabFragmentBinding.vocabularyList
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
            false)
        recyclerView.layoutManager = linearLayoutManager

        val vocabularyList = VocabularyList().list

        val vocabRecyclerAdapter = VocabRecyclerAdapter(context, vocabularyList.toMutableList())
        recyclerView.adapter = vocabRecyclerAdapter

        vocabRecyclerAdapter.clickListener = View.OnClickListener {
            val foreignWord = it.findViewById<TextView>(R.id.foreignWord)
            Toast.makeText(context, "Word ${foreignWord.text} clicked",
                Toast.LENGTH_SHORT).show()
        }
    }
}
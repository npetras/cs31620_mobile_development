package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uk.ac.aber.dcs.cs31620.vocabhelper.MainActivity
import uk.ac.aber.dcs.cs31620.vocabhelper.R
import uk.ac.aber.dcs.cs31620.vocabhelper.databinding.VocabularyFragmentBinding

/**
 * Set-ups data in the Vocabulary Fragment, creating a RecyclerView that contains the data from
 * the database.
 */
class VocabularyFragment : Fragment() {

    private lateinit var viewBinding: VocabularyFragmentBinding
    private val viewModel: VocabularyViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewBinding = VocabularyFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar!!.show()
        setupRecyclerView()
        return viewBinding.root
    }

    /**
     * Set-up the RecyclerView, attaching the [VocabularyRecyclerAdapter].
     * Ensure that data is updated, every time it changes, by observing the LiveData from the
     * Database.
     * When an item in the RecyclerView is clicked a toast message appears.
     */
    private fun setupRecyclerView() {
        val recyclerView = viewBinding.vocabularyList
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
            false)
        recyclerView.layoutManager = linearLayoutManager

        val vocabularyListLiveData = viewModel.vocabularyItems
        val vocabRecyclerAdapter = VocabularyRecyclerAdapter(context)
        recyclerView.adapter = vocabRecyclerAdapter

        vocabularyListLiveData.observe(viewLifecycleOwner) {
            vocabRecyclerAdapter.changeDataset(it.toMutableList())
        }

        vocabRecyclerAdapter.clickListener = View.OnClickListener {
            val foreignWord = it.findViewById<TextView>(R.id.foreignWord)
            Toast.makeText(context, "Word ${foreignWord.text} clicked",
                Toast.LENGTH_SHORT).show()
        }
    }
}
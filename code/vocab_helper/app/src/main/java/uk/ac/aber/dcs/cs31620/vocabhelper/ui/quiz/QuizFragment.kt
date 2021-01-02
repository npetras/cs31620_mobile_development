package uk.ac.aber.dcs.cs31620.vocabhelper.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uk.ac.aber.dcs.cs31620.vocabhelper.R

/**
 * Will handle the user's ability to review their Vocabulary, in a quiz style.
 */
class QuizFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        val root = inflater.inflate(R.layout.quiz_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_quiz)
        viewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}
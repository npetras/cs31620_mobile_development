package uk.ac.aber.dcs.cs31620.vocabhelper.ui.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.viewModels
import uk.ac.aber.dcs.cs31620.vocabhelper.MainActivity
import uk.ac.aber.dcs.cs31620.vocabhelper.R
import uk.ac.aber.dcs.cs31620.vocabhelper.databinding.IntroductionFragmentBinding

class IntroductionFragment : Fragment() {

    private val viewModel: IntroductionViewModel by viewModels()
    private lateinit var viewBinding: IntroductionFragmentBinding
    private lateinit var confirmButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = IntroductionFragmentBinding.inflate(inflater, container, false)
        confirmButton = viewBinding.confirmButton
        val nativeLangSpinner = viewBinding.nativeLangSpinner
        val foreignLangSpinner = viewBinding.foreignLangSpinner

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.language_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                nativeLangSpinner.adapter = adapter
                foreignLangSpinner.adapter = adapter
            }
        }

        (requireActivity() as MainActivity).supportActionBar!!.hide()
        return viewBinding.root
    }

    fun confirm(view: View) {

    }


}
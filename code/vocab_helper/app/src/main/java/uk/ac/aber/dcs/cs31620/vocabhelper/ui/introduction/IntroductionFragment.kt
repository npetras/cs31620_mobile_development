package uk.ac.aber.dcs.cs31620.vocabhelper.ui.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout
import uk.ac.aber.dcs.cs31620.vocabhelper.MainActivity
import uk.ac.aber.dcs.cs31620.vocabhelper.R
import uk.ac.aber.dcs.cs31620.vocabhelper.databinding.IntroductionFragmentBinding

class IntroductionFragment : Fragment() {

    private val viewModel: IntroductionViewModel by viewModels()
    private lateinit var viewBinding: IntroductionFragmentBinding
    private lateinit var confirmButton: Button
    private lateinit var nativeLangDropdown: TextInputLayout
    private lateinit var foreignLangDropdown: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = IntroductionFragmentBinding.inflate(inflater, container, false)
        confirmButton = viewBinding.confirmButton
        nativeLangDropdown = viewBinding.textInputLayoutNativeLang
        foreignLangDropdown = viewBinding.textInputLayoutForeignLang

        val adapter = context?.let { ArrayAdapter.createFromResource(it, R.array.language_array, R.layout.list_item) }
        (nativeLangDropdown.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (foreignLangDropdown.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        (requireActivity() as MainActivity).supportActionBar!!.hide()
        return viewBinding.root
    }

    fun confirmLanguageConfig(view: View) {
        
    }


}
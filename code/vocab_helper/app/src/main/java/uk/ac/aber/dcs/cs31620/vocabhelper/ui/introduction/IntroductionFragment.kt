package uk.ac.aber.dcs.cs31620.vocabhelper.ui.introduction

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.textfield.TextInputLayout
import uk.ac.aber.dcs.cs31620.vocabhelper.*
import uk.ac.aber.dcs.cs31620.vocabhelper.databinding.IntroductionFragmentBinding

/**
 * Controller for the Introduction fragment, which is the first screen the user is taken to.
 */
class IntroductionFragment : Fragment() {

    private lateinit var viewBinding: IntroductionFragmentBinding
    private lateinit var confirmButton: Button
    private lateinit var nativeLangDropdown: TextInputLayout
    private lateinit var foreignLangDropdown: TextInputLayout

    private lateinit var languageSettings: SharedPreferences
    private lateinit var navController: NavController

    private lateinit var validLanguageList: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = IntroductionFragmentBinding.inflate(inflater, container, false)
        confirmButton = viewBinding.confirmButton
        nativeLangDropdown = viewBinding.textInputLayoutNativeLang
        foreignLangDropdown = viewBinding.textInputLayoutForeignLang

        languageSettings = requireActivity().getPreferences(MODE_PRIVATE)
        navController = NavHostFragment.findNavController(this)

        validLanguageList = resources.getStringArray(R.array.language_array)

        setUpDropdowns()
        (requireActivity() as MainActivity).supportActionBar!!.hide()
        confirmLanguageConfig()

        return viewBinding.root
    }

    /**
     * Sets the right available values for the drop-downs
     */
    private fun setUpDropdowns() {
        val adapter = context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.language_array,
                R.layout.list_item
            )
        }
        (nativeLangDropdown.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (foreignLangDropdown.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    /**
     * Click Listener for the [confirmButton].
     * If the input is valid the settings are saved, otherwise the user is prompted for
     * a valid input.
     */
    private fun confirmLanguageConfig() {
        confirmButton.setOnClickListener {
            val nativeLang = nativeLangDropdown.editText?.text.toString()
            val foreignLang = foreignLangDropdown.editText?.text.toString()

            val isValidInput = isValidInput(nativeLang, foreignLang)

            if (isValidInput) {
                saveLanguageSettings(nativeLang, foreignLang)
            } else {
                promptUserForValidInput(nativeLang, foreignLang)
            }
        }
    }

    private fun isValidInput(nativeLang: String, foreignLang: String): Boolean {
        return (nativeLang != "" && foreignLang != "") &&
                    (nativeLang != foreignLang) &&
                    (validLanguageList.contains(nativeLang) && validLanguageList.contains(foreignLang))

    }

    private fun saveLanguageSettings(nativeLang: String, foreignLang: String) {
        with(languageSettings.edit()) {
            putString(NATIVE_LANG_PREF, nativeLang)
            putString(FOREIGN_LANG_PREF, foreignLang)
            apply()
        }
        navController.navigate(R.id.action_introduction_navigation_to_vocabulary_navigation)
    }

    private fun promptUserForValidInput(nativeLang: String, foreignLang: String) {
        if (nativeLang == "" || foreignLang == "") {
            promptUserToFillFields(nativeLang, foreignLang)
        }
        if (nativeLang == foreignLang) {
            promptUserToPickUniqueLanguages()
        }
        if (!validLanguageList.contains(nativeLang) || !validLanguageList.contains(foreignLang)) {
            promptUserForValidLanguage(nativeLang, foreignLang, validLanguageList)
        }
    }

    private fun promptUserToFillFields(nativeLang: String, foreignLang: String) {
        if (nativeLang == "") {
            nativeLangDropdown.error = getString(R.string.error_text_required_fields)
        }

        if (foreignLang == "") {
            foreignLangDropdown.error = getString(R.string.error_text_required_fields)
        }
    }

    private fun promptUserToPickUniqueLanguages() {
        nativeLangDropdown.error = getString(R.string.intro_error_text_same_language)
        foreignLangDropdown.error = getString(R.string.intro_error_text_same_language)
    }

    private fun promptUserForValidLanguage(nativeLang: String, foreignLang: String,
                                           validLanguageList: Array<String>) {
        if (!validLanguageList.contains(nativeLang)) {
            nativeLangDropdown.error = getString(R.string.intro_error_invalid_language)
        }
        if (!validLanguageList.contains(foreignLang)) {
            nativeLangDropdown.error = getString(R.string.intro_error_invalid_language)
        }
    }

}
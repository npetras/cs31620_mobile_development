package uk.ac.aber.dcs.cs31620.vocabhelper.ui.vocabulary.addingitems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import uk.ac.aber.dcs.cs31620.vocabhelper.R
import uk.ac.aber.dcs.cs31620.vocabhelper.databinding.AddVocabularyFragmentBinding
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyItem
import uk.ac.aber.dcs.cs31620.vocabhelper.model.WordType
import java.util.*

class AddVocabularyFragment : Fragment() {

    private val viewModel: AddVocabularyViewModel by viewModels()

    private lateinit var viewBinding: AddVocabularyFragmentBinding
    private lateinit var foreignWordTextInput: TextInputEditText
    private lateinit var translationTextInput: TextInputEditText
    private lateinit var wordTypeDropdown: TextInputLayout
    private lateinit var wordTypeDropdownField: MaterialAutoCompleteTextView
    private lateinit var definitionTextInput: TextInputEditText
    private lateinit var addButton: MaterialButton

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = AddVocabularyFragmentBinding.inflate(inflater, container, false)
        addButton = viewBinding.addVocabularyItemButton
        foreignWordTextInput = viewBinding.textInputForeignWord
        translationTextInput = viewBinding.textInputTranslation
        wordTypeDropdown = viewBinding.textInputLayoutWordType
        wordTypeDropdownField = viewBinding.textInputWordType
        definitionTextInput = viewBinding.textInputDefinition

        // set-up word type dropdown
        val items = WordType.values()
        val itemsAsStrings = mutableListOf<String>()
        val itemsAsStringsCapitalized = mutableListOf<String>()

        items.forEach {
            itemsAsStrings.add(it.toString())
        }
        itemsAsStrings.forEach {
            itemsAsStringsCapitalized.add(it.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT))
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, itemsAsStringsCapitalized)
        (wordTypeDropdown.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        navController = findNavController(this)

        addButton.setOnClickListener {
            val foreignWord = foreignWordTextInput.text.toString()
            val translation = foreignWordTextInput.text.toString()

            val errorText = "Please fill this field it is required"

            if (foreignWord == "" || translation == "") {
                if (foreignWord == "") {
                    foreignWordTextInput.error = errorText
                }

                if (translation == "") {
                    translationTextInput.error = errorText
                }
            } else {
                val newVocabItem = VocabularyItem(
                    foreignWord = foreignWord,
                    translation = translation,
                    wordType = WordType.valueOf(wordTypeDropdownField.text.toString().toUpperCase(
                        Locale.ROOT)),
                    definition = definitionTextInput.text.toString()
                )
                viewModel.repository.insertVocabItem(newVocabItem)
                navController
                    .navigate(R.id.action_add_vocabulary_navigation_to_vocabulary_navigation)
            }
        }

        return viewBinding.root
    }

}
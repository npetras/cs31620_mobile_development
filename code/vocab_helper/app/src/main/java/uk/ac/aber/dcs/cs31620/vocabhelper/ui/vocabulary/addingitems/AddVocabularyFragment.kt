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
import uk.ac.aber.dcs.cs31620.vocabhelper.model.database.entity.VocabularyItem
import uk.ac.aber.dcs.cs31620.vocabhelper.model.database.entity.WordType
import uk.ac.aber.dcs.cs31620.vocabhelper.model.database.VocabularyDatabase
// only way to import Locale.ROOT
import java.util.*

const val ERROR_TEXT = "Please fill this field it is required"

/**
 * Fragment to handle the adding of new vocabulary items
 */
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
        foreignWordTextInput = viewBinding.textInputForeignWord
        translationTextInput = viewBinding.textInputTranslation
        wordTypeDropdown = viewBinding.textInputLayoutWordType
        wordTypeDropdownField = viewBinding.textInputWordType
        definitionTextInput = viewBinding.textInputDefinition
        addButton = viewBinding.addVocabularyItemButton

        setUpWordTypeDropdown()
        navController = findNavController(this)
        setUpClickListenerOnAddButton()

        return viewBinding.root
    }

    /**
     * Click listener for the 'Add' button in this fragment.
     * If the required fields are filled then a new [VocabularyItem] is created and added to the
     * [VocabularyDatabase], otherwise the user is prompted to fill in the required fields
     * that are currently empty.
     */
    private fun setUpClickListenerOnAddButton() {
        addButton.setOnClickListener {
            val foreignWord = foreignWordTextInput.text.toString()
            val translation = foreignWordTextInput.text.toString()

            if (foreignWord == "" || translation == "") {
                promptUserToFillRequiredFields(foreignWord, translation)
            } else {
                createNewVocabItem(foreignWord, translation)
            }
        }
    }

    /**
     * Creates a [VocabularyItem], using the Strings entered by the user (including [foreignWord]
     * and [translation]), and adds it to the [VocabularyDatabase].
     */
    private fun createNewVocabItem(foreignWord: String, translation: String) {
        val wordType = if (wordTypeDropdownField.text.toString() != "") {
            WordType.valueOf(wordTypeDropdownField.text.toString().toUpperCase(Locale.ROOT))
        } else {
            WordType.NONE
        }

        val newVocabItem = VocabularyItem(
            foreignWord = foreignWord,
            translation = translation,
            wordType = wordType,
            definition = definitionTextInput.text.toString()
        )

        viewModel.repository.insertVocabItem(newVocabItem)
        navController
            .navigate(R.id.action_add_vocabulary_navigation_to_vocabulary_navigation)
    }

    /**
     * If [foreignWord] inclusive or [translation] are empty (have to be provided by the user),
     * then the user is prompted to enter the values for the empty required fields.
     * The prompt is in the form of an error message visible on the empty required fields.
     */
    private fun promptUserToFillRequiredFields(
        foreignWord: String,
        translation: String
    ) {
        if (foreignWord == "") {
            foreignWordTextInput.error = ERROR_TEXT
        }

        if (translation == "") {
            translationTextInput.error = ERROR_TEXT
        }
    }

    /**
     * Fill the [wordTypeDropdownField] with the right values.
     * The list of strings from [WordType] are formatted, before being added to the dropdown
     */
    private fun setUpWordTypeDropdown() {
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
    }
}
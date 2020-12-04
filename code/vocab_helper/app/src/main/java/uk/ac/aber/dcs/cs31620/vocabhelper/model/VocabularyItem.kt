package uk.ac.aber.dcs.cs31620.vocabhelper.model

/**
 *
 *
 * @param foreignWord The foreign word added to the vocabulary
 * @param translation The translation of the [foreignWord] in the user's native language
 * @param definition The definition of the [foreignWord] in the user's native language
 */
data class VocabularyItem(
    val foreignWord: String = "",
    val translation: String = "",
    val definition: String = "",
    val wordType: WordType = WordType.NONE
) {
}
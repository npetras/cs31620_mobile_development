package uk.ac.aber.dcs.cs31620.vocabhelper.model.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 *
 *
 * @param foreignWord The foreign word added to the vocabulary
 * @param translation The translation of the [foreignWord] in the user's native language
 * @param definition The definition of the [foreignWord] in the user's native language
 * @param wordType The type of word (e.g. noun, verb, etc.)
 */
@Entity(tableName = "vocabulary_item", primaryKeys = ["foreign_word", "translation", "word_type"])
data class VocabularyItem(
    @ColumnInfo(name = "foreign_word") var foreignWord: String = "",
    var translation: String = "",
    var definition: String = "",
    @ColumnInfo(name = "word_type") var wordType: WordType = WordType.NONE
) {
}
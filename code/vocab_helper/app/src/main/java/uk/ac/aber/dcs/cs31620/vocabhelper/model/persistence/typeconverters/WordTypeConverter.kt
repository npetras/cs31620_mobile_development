package uk.ac.aber.dcs.cs31620.vocabhelper.model.persistence.typeconverters

import androidx.room.TypeConverter
import uk.ac.aber.dcs.cs31620.vocabhelper.model.WordType

object WordTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toString(wordType: WordType) = wordType.toString()

    @TypeConverter
    @JvmStatic
    fun toWordType(wordType: String) = WordType.valueOf(wordType)
}
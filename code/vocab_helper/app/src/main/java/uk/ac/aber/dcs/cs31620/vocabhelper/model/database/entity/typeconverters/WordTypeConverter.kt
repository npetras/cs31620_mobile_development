package uk.ac.aber.dcs.cs31620.vocabhelper.model.database.entity.typeconverters

import androidx.room.TypeConverter
import uk.ac.aber.dcs.cs31620.vocabhelper.model.database.entity.WordType

object WordTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toString(wordType: WordType) = wordType.toString()

    @TypeConverter
    @JvmStatic
    fun toWordType(wordType: String) = WordType.valueOf(wordType)
}
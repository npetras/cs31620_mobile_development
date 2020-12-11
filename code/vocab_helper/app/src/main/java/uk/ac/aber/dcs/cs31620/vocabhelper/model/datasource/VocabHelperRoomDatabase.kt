package uk.ac.aber.dcs.cs31620.vocabhelper.model.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyDao
import uk.ac.aber.dcs.cs31620.vocabhelper.model.VocabularyItem
import uk.ac.aber.dcs.cs31620.vocabhelper.model.datasource.typeconverters.WordTypeConverter

@Database(entities = [VocabularyItem::class], version = 1)
@TypeConverters(WordTypeConverter::class)
abstract class VocabHelperRoomDatabase: RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
}
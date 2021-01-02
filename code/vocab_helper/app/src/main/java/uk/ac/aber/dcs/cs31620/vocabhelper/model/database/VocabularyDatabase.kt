package uk.ac.aber.dcs.cs31620.vocabhelper.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.ac.aber.dcs.cs31620.vocabhelper.model.database.entity.VocabularyItem
import uk.ac.aber.dcs.cs31620.vocabhelper.model.database.entity.typeconverters.WordTypeConverter

@Database(entities = [VocabularyItem::class], version = 1)
@TypeConverters(WordTypeConverter::class)
abstract class VocabularyDatabase: RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao

    companion object {
        private var instance: VocabularyDatabase? = null

        fun getInstance(context: Context): VocabularyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    VocabularyDatabase::class.java,
                    "faa_database"
                ).build()
            }
            return instance!!
        }
    }
}
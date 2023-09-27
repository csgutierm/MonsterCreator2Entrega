package cl.desafiolatam.monstercreator.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.desafiolatam.monstercreator.model.Monster

/**
 * Created by Cristian Vidal on 2019-09-26.
 */
// Annotates class to be a Room Database with a table (entity) of the Monster class
@Database(entities = [Monster::class], version = 1)
@TypeConverters(MonsterAttributesConverter::class)
abstract class MonsterRoomDataBase : RoomDatabase() {

    abstract fun monsterDao(): MonsterDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MonsterRoomDataBase? = null

        fun getDatabase(context: Context): MonsterRoomDataBase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MonsterRoomDataBase::class.java,
                    "monster_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

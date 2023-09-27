package cl.desafiolatam.monstercreator.app

import android.app.Application
import androidx.room.Room
import cl.desafiolatam.monstercreator.model.db.MonsterRoomDataBase

/**
 * Created by Cristian Vidal on 2019-10-01.
 */
class MonsterCreatorApplication : Application() {

    companion object {
        lateinit var database: MonsterRoomDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, MonsterRoomDataBase::class.java, "monster_database")
            .build()
    }
}
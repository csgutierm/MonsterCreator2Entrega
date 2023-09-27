package cl.desafiolatam.monstercreator.model

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import cl.desafiolatam.monstercreator.app.MonsterCreatorApplication
import cl.desafiolatam.monstercreator.model.db.MonsterDao

/**
 * Created by Cristian Vidal on 2019-09-26.
 */

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class MonsterRepository() : MonsterRepositoryInterface {

    private val monsterDao: MonsterDao = MonsterCreatorApplication.database.monsterDao()


    private val allMonsters: LiveData<List<Monster>> = this.monsterDao.getAllMonsters()

    override fun saveMonster(monster: Monster) {
        InsertAsyncTask(monsterDao).execute(monster)
    }

    override fun getAllMonsters() = allMonsters

    override fun clearAllMonsters() {
        val monsters = allMonsters.value?.toTypedArray()
        if (monsters != null) {
            DeleteAsyncTask(monsterDao).execute(*monsters)
        }
    }

    private class InsertAsyncTask internal constructor(private val dao:  MonsterDao) :
        AsyncTask<Monster, Void, Void>() {
        override fun doInBackground(vararg params: Monster): Void? {
            dao.insertMonster(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val dao: MonsterDao) :
        AsyncTask<Monster, Void, Void>() {
        override fun doInBackground(vararg params: Monster): Void? {
            dao.deleteAllMonsters(*params)
            return null
        }
    }


}
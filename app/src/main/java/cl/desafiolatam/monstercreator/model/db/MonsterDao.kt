package cl.desafiolatam.monstercreator.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import cl.desafiolatam.monstercreator.model.Monster

/**
 * Created by Cristian Vidal on 2019-09-26.
 */
@Dao
interface MonsterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMonster(monster: Monster)

    @Query("SELECT * FROM monster_table ORDER BY name ASC")
    fun getAllMonsters(): LiveData<List<Monster>>


    @Delete
    fun deleteAllMonsters(vararg monster: Monster)




}

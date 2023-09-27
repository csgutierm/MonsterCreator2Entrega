package cl.desafiolatam.monstercreator.model

import androidx.lifecycle.LiveData

interface MonsterRepositoryInterface {

    fun saveMonster(monster: Monster)
    fun getAllMonsters(): LiveData<List<Monster>>
    fun clearAllMonsters()

}
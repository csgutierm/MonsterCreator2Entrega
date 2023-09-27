package cl.desafiolatam.monstercreator.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.desafiolatam.monstercreator.model.AttributeStore
import cl.desafiolatam.monstercreator.model.AttributeType
import cl.desafiolatam.monstercreator.model.Monster
import cl.desafiolatam.monstercreator.model.MonsterAttributes
import cl.desafiolatam.monstercreator.model.MonsterGenerator
import cl.desafiolatam.monstercreator.model.MonsterRepository
import cl.desafiolatam.monstercreator.model.MonsterRepositoryInterface
import kotlinx.coroutines.launch

/**
 * Created by Cristian Vidal on 2019-09-27.
 */

// Class extends AndroidViewModel and requires application as a parameter.
class MonsterCreatorViewModel(
    private val generator: MonsterGenerator = MonsterGenerator(),
    private val monsterRepository: MonsterRepositoryInterface = MonsterRepository()
) : ViewModel() {

    private val monsterLiveData = MutableLiveData<Monster>()

    private val saveLiveData = MutableLiveData<Boolean>()

    fun getCreatureLiveData(): LiveData<Monster> = monsterLiveData

    fun getSaveLiveData(): LiveData<Boolean> = saveLiveData

    lateinit var monster: Monster

    var name = ObservableField<String>("")
    var intelligence = 0
    var ugliness = 0
    var evilness = 0
    var drawable = 0


    fun updateCreature() {
        val attributes = MonsterAttributes(intelligence, ugliness, evilness)
        monster = generator.generateMonster(attributes, name.get() ?: "", drawable)
        monsterLiveData.postValue(monster)
    }

    fun attributeSelected(attributeType: AttributeType, position: Int) {
        when(attributeType) {
            AttributeType.INTELLIGENCE -> {
                intelligence = AttributeStore.INTELLIGENCE[position].value
            }
            AttributeType.UGLINESS -> {
                ugliness = AttributeStore.UGLINESS[position].value
            }
            AttributeType.EVILNESS -> {
                evilness = AttributeStore.EVILNESS[position].value
            }
        }
        updateCreature()
    }

    fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        updateCreature()
    }
    fun saveCreature() {
        monsterRepository.saveMonster(monster)
        saveLiveData.postValue(true)
    }


}
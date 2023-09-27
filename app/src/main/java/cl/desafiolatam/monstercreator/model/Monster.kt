package cl.desafiolatam.monstercreator.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Cristian Vidal on 2019-09-26.
 */
@Entity(tableName = "monster_table")
data class Monster(
    val attributes: MonsterAttributes = MonsterAttributes(),
    val monsterPoints: Int = 0,
    @PrimaryKey @NonNull val name: String,
    val drawable: Int = 0
)

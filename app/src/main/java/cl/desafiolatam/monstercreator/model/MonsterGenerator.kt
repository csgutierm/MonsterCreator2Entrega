package cl.desafiolatam.monstercreator.model

/**
 * Created by Cristian Vidal on 2019-09-26.
 */
class MonsterGenerator {

    fun generateMonster(
        attributes: MonsterAttributes,
        name: String = "",
        drawable: Int = 0
    ): Monster {
        val monsterPoints = 4 * attributes.intelligence +
                3 * attributes.evilness +
                2 * attributes.ugliness
        return Monster( attributes , monsterPoints, name, drawable)
    }

}
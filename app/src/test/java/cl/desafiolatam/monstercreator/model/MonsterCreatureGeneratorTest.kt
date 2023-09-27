package cl.desafiolatam.monstercreator.model

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Cristian Vidal on 2019-09-26.
 */

class MonsterCreatureGeneratorTest {
    private lateinit var monsterGenerator: MonsterGenerator

    @Before
    fun setup() {
        monsterGenerator = MonsterGenerator()
    }

    @Test
    fun testMonsterPoints() {
        val attribute = MonsterAttributes(
            intelligence = 10,
            ugliness = 8,
            evilness = 4
        )
        val name = "cthulu"
        val expectedMonster = Monster( attribute, 68, name)

        assertEquals(expectedMonster, monsterGenerator.generateMonster(attribute, name))
    }


}
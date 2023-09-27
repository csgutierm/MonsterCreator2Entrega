package cl.desafiolatam.monstercreator.view.monsteravatars

import cl.desafiolatam.monstercreator.R
import cl.desafiolatam.monstercreator.model.MonsterImage

/**
 * Created by Cristian Vidal on 2019-10-02.
 */
object MonsterStore {
    val MONSTERS_IMAGE: List<MonsterImage> by lazy {
        val monsters = mutableListOf<MonsterImage>()

        monsters.add(MonsterImage(R.drawable.asset01))
        monsters.add(MonsterImage(R.drawable.asset02))
        monsters.add(MonsterImage(R.drawable.asset03))
        monsters.add(MonsterImage(R.drawable.asset04))
        monsters.add(MonsterImage(R.drawable.asset05))
        monsters.add(MonsterImage(R.drawable.asset06))

        monsters
    }
}

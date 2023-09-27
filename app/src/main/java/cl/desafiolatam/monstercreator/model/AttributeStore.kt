package cl.desafiolatam.monstercreator.model

object AttributeStore {
    val INTELLIGENCE: List<AttributeValue> by lazy {
        val monsterImages = mutableListOf<AttributeValue>()
        monsterImages.add(AttributeValue("Ninguno"))
        monsterImages.add(AttributeValue("Listillo", 3))
        monsterImages.add(AttributeValue("Vivaracho", 7))
        monsterImages.add(AttributeValue("Einstein", 10))
        monsterImages
    }

    val UGLINESS: List<AttributeValue> by lazy {
        val monsterImages = mutableListOf<AttributeValue>()
        monsterImages.add(AttributeValue("None"))
        monsterImages.add(AttributeValue("Feo", 3))
        monsterImages.add(AttributeValue("Adefesio feo", 7))
        monsterImages.add(AttributeValue("Feo feo", 10))
        monsterImages
    }

    val EVILNESS: List<AttributeValue> by lazy {
        val monsterImages = mutableListOf<AttributeValue>()
        monsterImages.add(AttributeValue("None"))
        monsterImages.add(AttributeValue("malito", 3))
        monsterImages.add(AttributeValue("malulo", 7))
        monsterImages.add(AttributeValue("Mephistofeles", 10))
        monsterImages
    }

}
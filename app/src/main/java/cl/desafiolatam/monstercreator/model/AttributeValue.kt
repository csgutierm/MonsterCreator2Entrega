package cl.desafiolatam.monstercreator.model

/**
 * Created by Cristian Vidal on 2019-10-02.
 */

data class AttributeValue(val name: String = "", val value: Int = 0) {
    override fun toString() = "$name: $value"
}
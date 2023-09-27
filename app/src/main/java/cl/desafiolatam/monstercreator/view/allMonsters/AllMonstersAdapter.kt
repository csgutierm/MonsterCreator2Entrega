package cl.desafiolatam.monstercreator.view.allMonsters

import android.view.ActionProvider.VisibilityListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.monstercreator.R
import cl.desafiolatam.monstercreator.model.Monster
import kotlinx.android.synthetic.main.monster_item.view.MonsterPoints
import kotlinx.android.synthetic.main.monster_item.view.monsterImageView
import kotlinx.android.synthetic.main.monster_item.view.name

class AllMonstersAdapter(private val monsters: MutableList<Monster>) :
    RecyclerView.Adapter<AllMonstersAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllMonstersAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.monster_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllMonstersAdapter.ViewHolder, position: Int) {
        holder.onBind(monsters[position])
    }

    override fun getItemCount(): Int {
        return monsters.size
    }

    fun updateMonster(monsters: List<Monster>){
        this.monsters.clear()
        this.monsters.addAll(monsters)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(monster: Monster) {
            with(monster) {
                itemView.monsterImageView.setImageResource(drawable)
                itemView.name.text = name
                itemView.MonsterPoints.text = monsterPoints.toString()
            }
        }

    }


}
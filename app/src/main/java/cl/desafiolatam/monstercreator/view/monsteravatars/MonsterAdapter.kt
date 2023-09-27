package cl.desafiolatam.monstercreator.view.monsteravatars

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.monstercreator.R
import cl.desafiolatam.monstercreator.model.Monster
import cl.desafiolatam.monstercreator.model.MonsterImage

/**
 * Created by Cristian Vidal on 2019-10-02.
 */
class MonsterAdapter(
    private val monsters: List<MonsterImage>,
    private val listener: MonsterListener
) : RecyclerView.Adapter<MonsterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_monster, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount() = monsters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(monsters[position])
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private lateinit var monster: MonsterImage

        private val imageView = itemView.findViewById<ImageView>(R.id.monsterAvatar)

        init {
            itemView.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(monsterImage: MonsterImage) {
            this.monster = monsterImage
            val bitmap = BitmapFactory.decodeResource(imageView.context.resources, monsterImage.drawable)
            imageView.setImageDrawable(BitmapDrawable(imageView.context.resources, bitmap))
        }

        override fun onClick(view: View) {
            listener.monsterClicked(this.monster)
        }
    }

    interface MonsterListener {
        fun monsterClicked(monsterImage: MonsterImage)
    }
}

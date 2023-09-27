package cl.desafiolatam.monstercreator.view.monster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import cl.desafiolatam.monstercreator.R
import cl.desafiolatam.monstercreator.databinding.ActivityMonsterCreatorBinding
import cl.desafiolatam.monstercreator.model.AttributeStore
import cl.desafiolatam.monstercreator.model.AttributeType
import cl.desafiolatam.monstercreator.model.AttributeValue
import cl.desafiolatam.monstercreator.model.MonsterImage
import cl.desafiolatam.monstercreator.view.monsteravatars.MonsterAdapter
import cl.desafiolatam.monstercreator.view.monsteravatars.MonsterBottomDialogFragment
import cl.desafiolatam.monstercreator.viewmodel.MonsterCreatorViewModel
import kotlinx.android.synthetic.main.activity_monster_creator.avatarImageView
import kotlinx.android.synthetic.main.activity_monster_creator.endurance
import kotlinx.android.synthetic.main.activity_monster_creator.hitPoints
import kotlinx.android.synthetic.main.activity_monster_creator.intelligence
import kotlinx.android.synthetic.main.activity_monster_creator.nameEditText
import kotlinx.android.synthetic.main.activity_monster_creator.strength
import kotlinx.android.synthetic.main.activity_monster_creator.tapLabel
import kotlinx.android.synthetic.main.monster_item.name

class MonsterCreatorActivity : AppCompatActivity(), MonsterAdapter.MonsterListener {

    private lateinit var binding: ActivityMonsterCreatorBinding
    private lateinit var viewModel: MonsterCreatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_monster_creator)
        viewModel = ViewModelProviders.of(this).get(MonsterCreatorViewModel::class.java)
        binding.viewmodel = viewModel

        configureUI()
        configureSpinnerAdapters()
        configureSpinnerListeners()
        configureClickListeners()
        configureLiveDataObservers()
    }

    private fun configureUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Añade una Monstruo"
        if (viewModel.drawable != 0) hideTapLabel()
    }


    private fun configureSpinnerAdapters() {
        intelligence.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.INTELLIGENCE
        )
        strength.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.UGLINESS
        )
        endurance.adapter = ArrayAdapter<AttributeValue>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AttributeStore.EVILNESS
        )
    }

    private fun configureSpinnerListeners() {
        intelligence.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.attributeSelected(
                    AttributeType.INTELLIGENCE,
                    position
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        strength.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.attributeSelected(AttributeType.UGLINESS, position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        endurance.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.attributeSelected(AttributeType.EVILNESS, position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun configureClickListeners() {
        avatarImageView.setOnClickListener {
            val bottomDialogFragment =
                MonsterBottomDialogFragment.newInstance()
            bottomDialogFragment.show(supportFragmentManager,
                "AvatarBottomDialogFragment")
        }
    }

    private fun configureLiveDataObservers() {
        viewModel.getCreatureLiveData().observe(this, Observer { creature ->
            creature.let {
                hitPoints.text = it.monsterPoints.toString()
                avatarImageView.setImageResource(it.drawable)
                nameEditText.setText(creature.name)
            }
        })

        viewModel.getSaveLiveData().observe(this, Observer { creature ->
            creature.let {
                if(it) {
                    Toast.makeText(this,"Monstruo guardado correctamente",Toast.LENGTH_SHORT).show()
                    finish()
                }
                else {
                    Toast.makeText(this,"Que ha ocurrido un problemita",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun monsterClicked(monsterImage: MonsterImage) {
        viewModel.drawableSelected(monsterImage.drawable)
        hideTapLabel()
    }
    private fun hideTapLabel() {
        tapLabel.visibility = View.INVISIBLE
    }


}

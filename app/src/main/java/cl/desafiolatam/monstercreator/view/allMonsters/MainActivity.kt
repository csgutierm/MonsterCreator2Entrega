package cl.desafiolatam.monstercreator.view.allMonsters

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatam.monstercreator.R
import cl.desafiolatam.monstercreator.R.*
import cl.desafiolatam.monstercreator.view.monster.MonsterCreatorActivity
import cl.desafiolatam.monstercreator.viewmodel.AllMonsterViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.rvMonsters

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AllMonsterViewModel

    private val adapter = AllMonstersAdapter(mutableListOf())

    //private val monsterRepository = MonsterRepository(MonsterCreatorApplication.database.monsterDao())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(this,MonsterCreatorActivity::class.java))
        }

        initRecyclerView()
    }

    private fun initRecyclerView(){
        viewModel = ViewModelProvider(this)[AllMonsterViewModel::class.java]
        rvMonsters.layoutManager = LinearLayoutManager(this)
        rvMonsters.adapter = adapter

        viewModel.getAllMonsters().observe(this) { monsters ->
            monsters?.let {
                adapter.updateMonster(it)
            }
        }

        /*val temporaryMonsters = MonsterGenerator()
        monsterRepository.saveMonster(temporaryMonsters.generateMonster(MonsterAttributes(10,12,15),"Pepe",R.drawable.asset01))
        monsterRepository.saveMonster(temporaryMonsters.generateMonster(MonsterAttributes(11,10,11),"Pablito",R.drawable.asset06))
        monsterRepository.saveMonster(temporaryMonsters.generateMonster(MonsterAttributes(14,14,13),"Anita",R.drawable.asset03))
        monsterRepository.saveMonster(temporaryMonsters.generateMonster(MonsterAttributes(15,12,14),"Jose",R.drawable.asset04))
        monsterRepository.saveMonster(temporaryMonsters.generateMonster(MonsterAttributes(12,11,12),"Armando",R.drawable.asset05))
    */
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
/*        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }*/
        return when (item.itemId) {
            id.action_settings -> {
                showDeleteConfirmationDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("¿Estás seguro de que deseas borrar la base de datos?")
        builder.setPositiveButton("Sí") { _, _ ->
            deleteDatabase()
        }
        builder.setNegativeButton("No") { _, _ ->
            // No hacer nada si se selecciona "No"
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteDatabase() {
        viewModel.clearAllMonsters()
        Snackbar.make(rvMonsters, "Base de datos borrada exitosamente", Snackbar.LENGTH_SHORT).show()
    }
}

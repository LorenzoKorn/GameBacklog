package lorenzokorn.gamebacklog.controller.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.setPadding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.game_card.*
import lorenzokorn.gamebacklog.R
import lorenzokorn.gamebacklog.controller.add.AddGame
import lorenzokorn.gamebacklog.controller.add.EXTRA_GAME
import lorenzokorn.gamebacklog.model.Game
import lorenzokorn.gamebacklog.model.GameAdapter

const val GAME_REQUEST_CODE = 1

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()

        // floating button
        fab.setOnClickListener {
            openAddGame()
        }
    }

    private fun initViews() {
        games_list.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        games_list.adapter = gameAdapter
        games_list.addItemDecoration(DividerItemDecoration(this@MainActivity, 0))
//        createItemTouchHelper().attachToRecyclerView(games_list)
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.games.observe(this, Observer { games ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun openAddGame() {
        val intent = Intent(this, AddGame::class.java)
        startActivityForResult(intent, GAME_REQUEST_CODE)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GAME_REQUEST_CODE -> {
                    val game = data!!.getParcelableExtra<Game>(EXTRA_GAME)
                    mainActivityViewModel.insertGame(game)
                }
            }
        }
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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

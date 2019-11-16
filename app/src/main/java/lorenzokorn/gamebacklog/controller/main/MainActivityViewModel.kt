package lorenzokorn.gamebacklog.controller.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lorenzokorn.gamebacklog.database.GameRepository
import lorenzokorn.gamebacklog.model.Game

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.IO)

    val games = gameRepository.getGames()

    fun insertGame(game: Game) {
        mainScope.launch {
            gameRepository.insertGame(game)
        }
    }
}
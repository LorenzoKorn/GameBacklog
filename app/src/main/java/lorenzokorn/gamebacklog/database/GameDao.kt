package lorenzokorn.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import lorenzokorn.gamebacklog.model.Game

@Dao
interface GameDao {
    /**
     * get all the games from room db
     */
    @Query("SELECT * FROM game ORDER BY date")
    fun getGames(): LiveData<List<Game>>

    /**
     * add a game to the room db
     */
    @Insert
    suspend fun insertGame(game: Game)

    /**
     * removes a game from the room db.
     * Returns it in case the user wants to undo his action
     */
    @Delete
    suspend fun deleteGame(game: Game)

    /**
     * removes all the games from the room db.
     * returns all the games in case the user wants to undo his action
     */
    @Query("DELETE FROM game")
    suspend fun deleteAllGames()
}
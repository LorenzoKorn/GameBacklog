package lorenzokorn.gamebacklog.controller.add

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.TypeConverters
import lorenzokorn.gamebacklog.R

import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import lorenzokorn.gamebacklog.controller.main.MainActivity
import lorenzokorn.gamebacklog.database.Converters
import lorenzokorn.gamebacklog.model.Game
import java.lang.Exception
import java.text.SimpleDateFormat

const val EXTRA_GAME = "EXTRA_GAME"

@TypeConverters(Converters::class)
class AddGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            onSave()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun onSave() {
        if (validateGame()) {
            val date =
                (add_game_day.text.toString() + "-" + add_game_month.text.toString() + "-" + add_game_year.text.toString())

            try {
                val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                dateFormat.isLenient = false
                val releaseDate = dateFormat.parse(date)

                val game = Game(
                    add_game_title.text.toString(),
                    add_game_platform.text.toString(),
                    releaseDate
                )

                val result = Intent()
                result.putExtra(EXTRA_GAME, game)
                setResult(Activity.RESULT_OK, result)

                finish()
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.date_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateGame(): Boolean {
        return when {
            add_game_title.text.toString().isBlank() -> {
                Toast.makeText(this, getString(R.string.title_error), Toast.LENGTH_SHORT).show()
                false
            }
            add_game_platform.text.toString().isBlank() -> {
                Toast.makeText(this, getString(R.string.platform_error), Toast.LENGTH_SHORT).show()
                false
            }
            add_game_day.text.toString().isBlank() || add_game_month.text.toString().isBlank() || add_game_year.text.toString().isBlank() -> {
                Toast.makeText(this, getString(R.string.date_error), Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}

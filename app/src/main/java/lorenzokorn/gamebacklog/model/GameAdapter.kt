package lorenzokorn.gamebacklog.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game_card.view.*
import lorenzokorn.gamebacklog.R
import java.text.SimpleDateFormat

class GameAdapter(private var games: List<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat")
        fun bind(game: Game) {
            val releaseDate = SimpleDateFormat("dd MMMM yyyy").format(game.date)

            itemView.game_title.text = game.title
            itemView.game_platform.text = game.platform
            itemView.game_date.text = ("release date: $releaseDate")
        }
    }
}
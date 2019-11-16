package lorenzokorn.gamebacklog.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lorenzokorn.gamebacklog.R

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
        private val title: TextView = itemView.findViewById(R.id.game_title)
        private val platform: TextView = itemView.findViewById(R.id.game_platform)
        private val date: TextView = itemView.findViewById(R.id.game_date)

        fun bind(game: Game) {
            title.text = game.title
            platform.text = game.platform
            date.text = game.date.time.toString()
        }
    }
}
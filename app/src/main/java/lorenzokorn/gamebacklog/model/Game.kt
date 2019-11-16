package lorenzokorn.gamebacklog.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "game")
data class Game(
    var title: String,
    var platform: String,
    var date: Date,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
) : Parcelable
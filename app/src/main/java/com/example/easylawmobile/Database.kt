import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [User::class], version = 3)
@TypeConverters(Converters::class)
abstract class database : RoomDatabase() {
    companion object {
        private var INSTANCE: database? = null
        fun getInstance(context: Context): database {
            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            context, database::class.java,
                            "lawdata"
                        ).build()
                    INSTANCE = instance
                }
                return instance as database
            }
        }
    }
}


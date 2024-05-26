import androidx.room.TypeConverter
import com.example.easylawmobile.data.models.Access
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date


class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromAccessList(accessList: List<Access>?): String {
        return gson.toJson(accessList)
    }

    @TypeConverter
    fun toAccessList(accessListString: String): List<Access>? {
        val listType = object : TypeToken<List<Access>>() {}.type
        return gson.fromJson(accessListString, listType)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
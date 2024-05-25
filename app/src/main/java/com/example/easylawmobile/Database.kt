import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.easylawmobile.data.dao.AbonnementDao
import com.example.easylawmobile.data.dao.AccessDao
import com.example.easylawmobile.data.dao.DomainInteretsDao
import com.example.easylawmobile.data.dao.FactureDao
import com.example.easylawmobile.data.dao.ServiceDao
import com.example.easylawmobile.data.models.Abonnement
import com.example.easylawmobile.data.models.Access
import com.example.easylawmobile.data.models.DomainInterets
import com.example.easylawmobile.data.models.Facture
import com.example.easylawmobile.data.models.Service
import com.example.easylawmobile.data.models.ServiceAccess
import com.example.easylawmobile.data.models.User
import com.example.easylawmobile.data.models.UserDomainInterts


@Database(
    entities = [
        User::class,
        Service::class,
        Facture::class,
        Access::class,
        DomainInterets::class,
        UserDomainInterts::class,
        ServiceAccess::class,
        Abonnement::class
    ],
    version = 3
)
@TypeConverters(Converters::class)
abstract class database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun serviceDao(): ServiceDao
    abstract fun factureDao(): FactureDao
    abstract fun accessDao(): AccessDao
    abstract fun domainInteretsDao(): DomainInteretsDao
    abstract fun abonnementDao(): AbonnementDao

    companion object {
        @Volatile
        private var INSTANCE: database? = null

        fun getInstance(context: Context): database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    "lawdata"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


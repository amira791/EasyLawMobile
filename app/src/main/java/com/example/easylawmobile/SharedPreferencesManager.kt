import android.content.Context
import androidx.compose.runtime.mutableStateOf

class SharedPreferencesManager(context: Context) {

    private val pref = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    val Localuser = mutableStateOf<String?>(null)

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLocalUsername(user: String) {
        pref.edit().putString(KEY_LOCAL_USER, user).apply()
    }

    fun getLocalUsername(): String? {
        return pref.getString(KEY_LOCAL_USER, null)
    }

    fun saveToken(token: String) {
        pref.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return pref.getString(KEY_TOKEN, null)
    }

    fun saveUserRole(role: String) {
        pref.edit().putString(KEY_USER_ROLE, role).apply()
    }

    fun getUserRole(): String? {
        return pref.getString(KEY_USER_ROLE, null)
    }

    fun setLoggedIn(value: Boolean) {
        pref.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()
    }

    // Subscription status functions
    fun isSubscribed(): Boolean {
        return pref.getBoolean(KEY_IS_SUBSCRIBED, false)
    }

    fun setSubscribed(isSubscribed: Boolean) {
        pref.edit().putBoolean(KEY_IS_SUBSCRIBED, isSubscribed).apply()
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_LOCAL_USER = "Localuser"
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_IS_SUBSCRIBED = "is_subscribed" // New key for subscription status
    }
}

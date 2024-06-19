import android.content.Context
import androidx.compose.runtime.mutableStateOf

class SharedPreferencesManager(context: Context) {

    private val pref = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    var Localuser = mutableStateOf<String?>(null)
    var plan3 = mutableStateOf<Int?>(0)

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLocalUsername(user: String) {
        pref.edit().putString(KEY_LOCAL_USER, user).apply()
        Localuser.value = user
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

    fun isSubscribed(): Boolean {
        return pref.getBoolean(KEY_IS_SUBSCRIBED, false)
    }

    fun setSubscribed(isSubscribed: Boolean) {
        pref.edit().putBoolean(KEY_IS_SUBSCRIBED, isSubscribed).apply()
    }

    fun setPlan3(value: Int?) {
        pref.edit().putInt(KEY_PLAN3, value ?: 0).apply()
        plan3.value = value
    }

    fun getPlan3(): Int? {
        return if (pref.contains(KEY_PLAN3)) pref.getInt(KEY_PLAN3, 0) else null
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_LOCAL_USER = "Localuser"
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_IS_SUBSCRIBED = "is_subscribed"
        private const val KEY_PLAN3 = "plan3"
    }
}

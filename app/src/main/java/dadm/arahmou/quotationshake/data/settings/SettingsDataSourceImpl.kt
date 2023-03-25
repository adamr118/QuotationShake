package dadm.arahmou.quotationshake.data.settings

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(private val sharedPreferences:SharedPreferences): SettingsDataSource {
    private object PreferenceKeys {
        const val USER_NAME = "prefs_username"
        const val LANGUAGE = "prefs_language"
    }
    private fun getUsernamePreference() =sharedPreferences.getString(PreferenceKeys.USER_NAME, "") ?: ""
    private fun getLanguagePreference() =sharedPreferences.getString(PreferenceKeys.LANGUAGE, "") ?: "en"



    override fun getUsername(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (PreferenceKeys.USER_NAME == key) {
                    trySend(getUsernamePreference()) }
            } }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        trySend(getUsernamePreference())
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener) } }.flowOn(
        Dispatchers.IO)

    override fun getLanguage(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (PreferenceKeys.LANGUAGE == key) {
                    trySend(getLanguagePreference()) }
            } }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        trySend(getLanguagePreference())
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener) } }.flowOn(
        Dispatchers.IO)
}
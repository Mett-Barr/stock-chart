package com.example.stockchart.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.stockchart.IsComposeMode
import com.example.stockchart.network.RequestMode
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = "settings"
)

@Singleton
class SettingsDataStore @Inject constructor(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>) {

    companion object {
        private val IS_COMPOSE_MODE = booleanPreferencesKey("is_compose_mode")
        private val IS_MOSHI_CONVERTER = booleanPreferencesKey("is_moshi_converter")
        private val REQUEST_MODE = stringPreferencesKey("request_mode")
    }

    suspend fun getComposeMode(): IsComposeMode {
        val preferences = dataStore.data.first()
        val mode = preferences[IS_COMPOSE_MODE] ?: true
        return IsComposeMode.getEnumState(mode)
    }

    suspend fun saveComposeMode(isComposeMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_COMPOSE_MODE] = isComposeMode
        }
    }

    suspend fun getRequestMode(): RequestMode {
        val preferences = dataStore.data.first()
        val mode = preferences[REQUEST_MODE]
        return RequestMode.valueOf(mode ?: RequestMode.GSON_RETROFIT.name)
    }

    suspend fun saveRequestMode(requestMode: RequestMode) {
        dataStore.edit { preferences ->
            preferences[REQUEST_MODE] = requestMode.name
        }
    }

//    private fun converterTypeSwitch(type: ConverterType): Boolean {
//        return when (type) {
//            ConverterType.GSON -> false
//            ConverterType.MOSHI -> true
//        }
//    }
//    private fun converterTypeSwitch(boolean: Boolean): ConverterType {
//        return when (boolean) {
//            true -> ConverterType.MOSHI
//            false -> ConverterType.GSON
//        }
//    }
//    suspend fun getConverterType(): Boolean {
//        val preferences = dataStore.data.first()
//        return preferences[IS_MOSHI_CONVERTER] ?: false
//    }
//
//    suspend fun saveConverterType(isComposeMode: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[IS_MOSHI_CONVERTER] = isComposeMode
//        }
//    }
}

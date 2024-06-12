package com.wooyj.datalayer.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.wooyj.datalayer.data.local.datastore.UserInfoDataStoreRepo.PreferencesKeys.ACCESS_TOKEN
import com.wooyj.datalayer.data.local.datastore.UserInfoDataStoreRepo.PreferencesKeys.AUTO_LOGIN
import com.wooyj.datalayer.data.local.datastore.UserInfoDataStoreRepo.PreferencesKeys.PASSWORD
import com.wooyj.datalayer.data.local.datastore.UserInfoDataStoreRepo.PreferencesKeys.REFRESH_TOKEN
import com.wooyj.datalayer.data.local.datastore.UserInfoDataStoreRepo.PreferencesKeys.TOKEN_TYPE
import com.wooyj.datalayer.data.local.datastore.UserInfoDataStoreRepo.PreferencesKeys.USER_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

// 아주 예전에 자동로그인 기능 붙인다고 했을때 썼던 Preference DataStore 들고왔습니다... 이게 맞는지는 잘 모르겠네요.
class UserInfoDataStoreRepo(private val dataStore: DataStore<Preferences>) {
    private object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val TOKEN_TYPE = stringPreferencesKey("token_type")
        val USER_ID = stringPreferencesKey("user_id")
        val PASSWORD = stringPreferencesKey("password")
        val AUTO_LOGIN = booleanPreferencesKey("auto_login")
    }

    val accessToken: Flow<String>
        get() =
            dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[ACCESS_TOKEN].toString()
                }

    val tokenType: Flow<String>
        get() =
            dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[TOKEN_TYPE].toString()
                }

    val refreshToken: Flow<String>
        get() =
            dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[REFRESH_TOKEN].toString()
                }

    val userId: Flow<String>
        get() =
            dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[USER_ID].toString()
                }

    val password: Flow<String>
        get() =
            dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[PASSWORD].toString()
                }

    val autoLogin: Flow<Boolean>
        get() =
            dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[AUTO_LOGIN] ?: false
                }

    suspend fun saveAutoLoginInfo(autologin: Boolean) {
        dataStore.edit {
            it[AUTO_LOGIN] = autologin
        }
    }

    suspend fun savePassword(userPW: String) {
        dataStore.edit {
            it[PASSWORD] = userPW
        }
    }

    suspend fun saveLoginInfo(
        userId: String,
        userPW: String,
    ) {
        dataStore.edit {
            it[USER_ID] = userId
            it[PASSWORD] = userPW
        }
    }

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    ) {
        dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun saveTokenType(tokenType: String) {
        dataStore.edit {
            it[TOKEN_TYPE] = tokenType
        }
    }
}

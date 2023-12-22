package com.capstone.learnfonify.data.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class SessionPreference private constructor(private val dataStore: DataStore<Preferences>){
    private val TOKEN_KEY = stringPreferencesKey("token_session")
    private val ID_USER = intPreferencesKey("id_user")

     fun getSessionLogin(): Flow<Boolean> {

        return dataStore.data.map { preferences->
            !preferences[TOKEN_KEY].equals(null)
        }
    }

    suspend fun saveTokenSession( token: String, id_user: Int){
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[ID_USER] = id_user
        }
    }



     fun getIdUser(): Int{
        var user_id: Int = -1
        runBlocking {
            dataStore.edit {preferences->
                user_id = preferences[ID_USER] ?: -1
            }
        }
        return user_id
    }

    suspend fun getSessionToken(): String{
        var token = ""
        dataStore.edit {preferences->
            token = preferences[TOKEN_KEY].toString() ?: ""
        }
        return token

    }

    suspend fun removeSession(){
        dataStore.edit {preferences->
            preferences.remove(TOKEN_KEY)
            preferences.remove(ID_USER)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SessionPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): SessionPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SessionPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
package com.example.datastoredemo

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class DataStoreHelper(context: Context) {
    private val datastore = context.createDataStore(name = "mydatastore")
    companion object{
        private val isUserLogin = preferencesKey<Boolean>("isUserLogin")
        private val userToken = preferencesKey<String>("userToken")
        private val userDataKey = preferencesKey<UserData>("userData")
    }
    suspend fun saveUserData(userData: UserData){
        datastore.edit {
            it[userDataKey]=userData
        }
    }

    suspend fun saveUserToken(token: String){
        datastore.edit {
            it[userToken]=token
        }
    }

    val userTokenUpdate = datastore.data.map {
        it[userToken]?:""
    }

    val userData = datastore.data.map {
        it[userDataKey]?:null
    }
}
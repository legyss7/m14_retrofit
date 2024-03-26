package com.hw14

import android.os.Build
import android.util.Log

import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _userData = MutableLiveData<UserModel?>()
    val userData: MutableLiveData<UserModel?> get() = _userData

    @RequiresApi(Build.VERSION_CODES.P)
    fun fetchUserData() {
        viewModelScope.launch {
            try {
                val response: UserModel = RetrofitInstance.searchUser.getUser()
                if (response.results.isNotEmpty()) {
                    _userData.value = response
                }
            } catch (e: Exception) {
                Log.d("myLog", "${e.message}")
            }
        }
    }
}
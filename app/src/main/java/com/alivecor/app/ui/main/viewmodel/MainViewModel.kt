package com.alivecor.app.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alivecor.app.data.UserProfile

class MainViewModel : ViewModel() {
    private val userProfileLiveData: MutableLiveData<UserProfile> = MutableLiveData()

    fun getUserProfile(): MutableLiveData<UserProfile> = userProfileLiveData

    fun saveValues(firstName:String,lastName:String,dob:Long){
        UserProfile(firstName,lastName,dob).also {
            it.toString()
            userProfileLiveData.value = it
        }
    }
}
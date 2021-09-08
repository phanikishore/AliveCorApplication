package com.alivecor.app.data

import com.alivecor.app.util.DOB_FORMAT
import com.alivecor.app.util.toFormattedString

data class UserProfile(val firstName:String,val lastName:String,val dob: Long){
    override fun toString(): String {
        return "UserProfile(firstName='$firstName', lastName='$lastName', dob=${dob.toFormattedString(
            DOB_FORMAT)})"
    }
}


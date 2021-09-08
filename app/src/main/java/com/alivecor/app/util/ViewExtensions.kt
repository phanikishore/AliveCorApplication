package com.alivecor.app.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alivecor.app.R

fun AppCompatActivity.navigate( fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container, fragment)
        addToBackStack(tag)
        commit()
    }
}
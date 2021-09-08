package com.alivecor.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alivecor.app.databinding.MainActivityBinding
import com.alivecor.app.ui.main.fragment.UserProfileFragment
import com.alivecor.app.ui.main.viewmodel.MainViewModel
import com.alivecor.app.util.navigate

class MainActivity : AppCompatActivity() {
    private val viewBinding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        ViewModelProvider(this).get(MainViewModel::class.java)
        if (savedInstanceState == null) {
            UserProfileFragment().also {
                navigate(it, it.mTAG)
            }
        }
    }
}


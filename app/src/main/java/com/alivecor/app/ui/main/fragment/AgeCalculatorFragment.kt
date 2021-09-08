package com.alivecor.app.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alivecor.app.R
import com.alivecor.app.databinding.FragmentAgeCalculatorBinding
import com.alivecor.app.ui.main.viewmodel.MainViewModel
import com.alivecor.app.util.getAge

/**
 * AgeCalculatorFragment used for displaying the age of the User in Age Format.
 */
class AgeCalculatorFragment : Fragment(R.layout.fragment_age_calculator) {
    val mTAG: String = AgeCalculatorFragment::class.java.simpleName

    private var viewBinding: FragmentAgeCalculatorBinding? = null
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentAgeCalculatorBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.getUserProfile().observe(requireActivity(), { userProfile ->
            viewBinding?.txtViewFirstName?.text =
                getString(
                    R.string.colon_format,
                    getString(R.string.first_name),
                    userProfile.firstName
                )
            viewBinding?.txtViewLastName?.text =
                getString(
                    R.string.colon_format,
                    getString(R.string.last_name),
                    userProfile.lastName
                )
            userProfile.dob.getAge().also {
                viewBinding?.txtViewDOB?.text =
                    getString(
                        R.string.colon_format, getString(R.string.age),
                        getString(R.string.age_format, it.first, it.second, it.third)
                    )
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.getUserProfile().removeObservers(requireActivity())
    }
}
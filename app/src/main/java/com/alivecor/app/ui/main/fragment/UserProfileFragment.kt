package com.alivecor.app.ui.main.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alivecor.app.R
import com.alivecor.app.databinding.FragmentUserProfileBinding
import com.alivecor.app.ui.main.viewmodel.MainViewModel
import com.alivecor.app.util.DOB_FORMAT
import com.alivecor.app.util.navigate
import com.alivecor.app.util.toDateLong
import com.alivecor.app.util.toFormattedString
import java.util.*


/**
 * UserProfileFragment class takes inputs from user.
 * 1. Display Date Picker Dialog for Date of Birth Selection.
 */
class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    val mTAG: String = UserProfileFragment::class.java.simpleName

    private var viewBinding: FragmentUserProfileBinding? = null
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentUserProfileBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewBinding?.btnNext?.setOnClickListener(btnNextClicked)
        viewBinding?.editTextDOB?.setOnTouchListener(touchListener)
    }

    /**
     * Touch Event Listener to show date dialog when Calendar drawable touched
     */
    @SuppressLint("ClickableViewAccessibility")
    private val touchListener = View.OnTouchListener { _, event ->
        val drawableEnd = 2
        if (event.action == MotionEvent.ACTION_UP) {
            viewBinding?.let { root ->
                if (event.rawX >= (root.editTextDOB.right - root.editTextDOB.compoundDrawables[drawableEnd].bounds.width())) {
                    showDateDialog()
                    return@OnTouchListener true
                }
            }
        }
        return@OnTouchListener false
    }

    private val btnNextClicked = View.OnClickListener {
        viewBinding?.let { layout ->
            val firstName = layout.editTextFirstName.text?.toString() ?: ""
            val lastName = layout.editTextLastName.text?.toString() ?: ""
            val dob = layout.editTextDOB.text?.toString()?.toDateLong(DOB_FORMAT) ?: 0L
            println("$mTAG: $dob")
            viewModel.saveValues(firstName, lastName, dob).also {
                AgeCalculatorFragment().also {
                    (requireActivity() as AppCompatActivity).navigate(it, it.mTAG)
                }
            }
        }
    }

    private fun showDateDialog() {
        DatePickerDialogFragment { dateFormat ->
            dateFormat?.let {
                viewBinding?.editTextDOB?.setText(it.toFormattedString(DOB_FORMAT))
            }
        }.showNow(parentFragmentManager, "DOB Selection")
    }
}
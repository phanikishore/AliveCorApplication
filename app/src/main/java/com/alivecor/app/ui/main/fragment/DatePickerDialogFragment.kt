package com.alivecor.app.ui.main.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alivecor.app.R
import com.alivecor.app.databinding.DialogDatePickerBinding
import com.alivecor.app.util.DOB_FORMAT
import com.alivecor.app.util.toFormattedString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

/**
 * class DatePickerDialogFragment used for showing Date Picker as Spinner
 */
class DatePickerDialogFragment(private val selectedDate: (Long?) -> Unit) : BottomSheetDialogFragment() {
    private val mTAG = DatePickerDialogFragment::class.java.simpleName
    lateinit var dialogBinding: DialogDatePickerBinding
    private var mSelectedDate: Long? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogBinding = DialogDatePickerBinding.inflate(inflater, container, false)
        val today = Calendar.getInstance()
        dialogBinding.datePickerDOB.apply {
            maxDate = today.timeInMillis
            init(
                today[Calendar.YEAR],
                today[Calendar.MONTH],
                today[Calendar.DAY_OF_MONTH]
            ) { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                mSelectedDate = calendar.timeInMillis
            }
        }
        dialogBinding.btnSubmit.setOnClickListener {
            println("DOB: ${mSelectedDate}")
            println("DOB: ${mSelectedDate?.toFormattedString(DOB_FORMAT)}")
            selectedDate.invoke(mSelectedDate)
            dialog?.dismiss()
        }
        return dialogBinding.root
    }
}
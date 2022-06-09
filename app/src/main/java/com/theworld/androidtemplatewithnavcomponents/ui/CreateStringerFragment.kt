package com.theworld.androidtemplatewithnavcomponents.ui

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.theworld.androidtemplatewithnavcomponents.R
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.CreateStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.EditStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.response.Stringer
import com.theworld.androidtemplatewithnavcomponents.databinding.FragmentCreateStringerBinding
import com.theworld.androidtemplatewithnavcomponents.databinding.FragmentEditStringerBinding
import com.theworld.androidtemplatewithnavcomponents.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class CreateStringerFragment : Fragment(R.layout.fragment_create_stringer) {

    companion object {
        private const val TAG = "CreateStringerFragment"
    }

    private var _binding: FragmentCreateStringerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StringerViewModel by viewModels()


    /*----------------------------------------- On Create View -------------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreateStringerBinding.inflate(inflater)
        return binding.root
    }

    /*----------------------------------------- On ViewCreated -------------------------------*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        clickListener()
    }

    private fun clickListener() {
        binding.edtStartTime.editText!!.setOnClickListener {
            displayTimePicker(binding.edtStartTime.editText!!)
        }

        binding.edtEndTime.editText!!.setOnClickListener {
            displayTimePicker(binding.edtEndTime.editText!!)
        }
    }


    /*----------------------------------------- Init -------------------------------*/
    private fun init() {

        binding.btnSubmit.setOnClickListener {

            // Validation is Pending for saving time


            if (!binding.edtAddress.customValidation(
                    CustomValidation()
                )
                or
                !binding.edtAge.customValidation(
                    CustomValidation()
                )
                or
                !binding.edtName.customValidation(
                    CustomValidation()
                )
                or
                !binding.edtMobile.customValidation(
                    CustomValidation(isLengthRequired = true, length = 10)
                )
                or
                !binding.edtStartTime.customValidation(
                    CustomValidation()
                )
                or
                !binding.edtEndTime.customValidation(
                    CustomValidation()
                )

            ) {
                return@setOnClickListener
            }


            val data = CreateStringerRequestData(
                address = binding.edtAddress.normalText(),
                name = binding.edtName.normalText(),
                age = binding.edtAge.normalText().toInt(),
                phoneNumber = binding.edtMobile.normalText(),
                password = "password",  // Since API missing, for sake of doing
                startTiming = binding.edtStartTime.normalText(),
                closeTiming = binding.edtEndTime.normalText(),
            )

            createData(data)
        }

    }

    private fun createData(data: CreateStringerRequestData) {
        collectLatestFlow(viewModel.createStringer(data)) { resource ->

            binding.loadingSpinner.isVisible = resource is Resource.Loading

            when (resource) {
                is Resource.Success -> {
                    requireView().snackbar("Data Inserted!")
                    viewModel.isDataChanged()
                    findNavController().navigateUp()
                }
                is Resource.Failure -> {
                    handleApiError(resource)
                }

                else -> Unit
            }
        }
    }


    /*----------------------------------------- Display  Time Picker -------------------------------*/

    private fun displayTimePicker(editText: EditText) {

        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()

        materialTimePicker.addOnPositiveButtonClickListener {
            val newHour: Int = materialTimePicker.hour
            val newMinute: Int = materialTimePicker.minute

            /*val time = if (newHour < 9) {
                "0$newHour:$newMinute"
            } else {
                "$newHour:$newMinute"
            }*/

            val time = String.format("%02d:%02d", newHour, newMinute)
            editText.setText(time)
        }

        materialTimePicker.show(requireActivity().supportFragmentManager, "fragment_tag")


    }


    /*----------------------------------------- On DestroyView -------------------------------*/

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
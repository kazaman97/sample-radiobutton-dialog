package com.example.sample_radiobutton_dialog

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.sample_radiobutton_dialog.data.parcelable.SampleDataParcelable
import com.example.sample_radiobutton_dialog.databinding.FragmentSampleDialogBinding

internal class SampleDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentSampleDialogBinding

    private var dataArrayList: ArrayList<SampleDataParcelable>? = null

    private var selectedId: Int = 0

    private var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            dataArrayList = it.getParcelableArrayList("data_array_list")
            selectedId = it.getInt("selected_id")
        }

        listener = activity?.let {
            it as Listener
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_sample_dialog,
            null,
            false
        )
        val view = binding.root
        val dialog = Dialog(view.context)
        dialog.setContentView(view)
        isCancelable = false

        dataArrayList?.forEach {
            val radioButton = RadioButton(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
                id = it.id
                text = it.title
            }
            val radioGroup = binding.radioGroup.apply {
                addView(radioButton)
            }
            if (it.id == selectedId) {
                radioGroup.check(selectedId)
            }
        }

        binding.negative.setOnClickListener {
            listener?.onClickSampleDialogNegative(dialog)
            dismiss()
        }

        binding.positive.setOnClickListener {
            listener?.onClickSampleDialogPositive(dialog, binding.radioGroup.checkedRadioButtonId)
            dismiss()
        }

        return dialog
    }

    interface Listener {
        fun onClickSampleDialogPositive(dialog: Dialog, id: Int)
        fun onClickSampleDialogNegative(dialog: Dialog)
    }

    class Builder {
        private var dataArrayList: ArrayList<SampleDataParcelable>? = null

        private var selectedId: Int = 0

        fun setDataArrayList(dataArrayList: ArrayList<SampleDataParcelable>): Builder {
            this.dataArrayList = dataArrayList
            return this
        }

        fun setSelectedId(selectedId: Int): Builder {
            this.selectedId = selectedId
            return this
        }

        fun build(): SampleDialogFragment {
            val args = Bundle()
            args.putParcelableArrayList("data_array_list", dataArrayList)
            args.putInt("selected_id", selectedId)
            return SampleDialogFragment().apply {
                arguments = args
            }
        }
    }
}

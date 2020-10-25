package com.example.sample_radiobutton_dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.sample_radiobutton_dialog.data.parcelable.SampleDataParcelable
import com.example.sample_radiobutton_dialog.databinding.FragmentSampleDialogBinding

internal class SampleDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentSampleDialogBinding

    private var dataArrayList: ArrayList<SampleDataParcelable>? = null

    private var selectedId: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            dataArrayList = it.getParcelableArrayList("data_array_list")
            selectedId = it.getInt("selected_id")
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

        binding.negative.setOnClickListener {
            dismiss()
        }

        binding.positive.setOnClickListener {
        }

        return dialog
    }

    class Builder() {
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

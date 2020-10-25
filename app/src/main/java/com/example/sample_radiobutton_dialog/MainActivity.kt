package com.example.sample_radiobutton_dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sample_radiobutton_dialog.data.parcelable.SampleDataParcelable
import com.example.sample_radiobutton_dialog.databinding.ActivityMainBinding

internal class MainActivity : AppCompatActivity(), SampleDialogFragment.Listener {
    companion object {
        private const val DIALOG = "DIALOG"
    }

    private lateinit var binding: ActivityMainBinding

    private var selectedId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val sampleData = arrayListOf<SampleDataParcelable>().apply {
            (1..100).toList().forEach {
                this.add(SampleDataParcelable(it, "title$it"))
            }
        }

        binding.addFab.setOnClickListener {
            SampleDialogFragment.Builder()
                .setDataArrayList(sampleData)
                .setSelectedId(selectedId)
                .build()
                .show(supportFragmentManager, DIALOG)
        }
    }

    override fun onClickSampleDialogPositive(dialog: Dialog, id: Int) {
        selectedId = id
        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onClickSampleDialogNegative(dialog: Dialog) {
    }
}

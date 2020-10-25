package com.example.sample_radiobutton_dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sample_radiobutton_dialog.databinding.ActivityMainBinding

internal class MainActivity : AppCompatActivity() {
    companion object {
        private const val DIALOG = "DIALOG"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.addFab.setOnClickListener {
            SampleDialogFragment.Builder()
                .setSelectedId(3)
                .build()
                .show(supportFragmentManager, DIALOG)
        }
    }
}

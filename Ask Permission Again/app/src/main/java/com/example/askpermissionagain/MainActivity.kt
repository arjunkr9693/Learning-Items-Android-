package com.example.askpermissionagain

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val requestPhonePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission granted, perform the action
                makePhoneCall()
            } else {
                // Permission denied, request again
                requestPhonePermissionWithRationale()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callButton = findViewById<Button>(R.id.callButton)
        callButton.setOnClickListener {
            requestPhonePermissionWithRationale()
        }
    }

    private fun requestPhonePermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission already granted, perform the action
            makePhoneCall()
        } else {
            // Request permission with rationale
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CALL_PHONE
                )
            ) {
                // Provide rationale message
                Toast.makeText(
                    this,
                    "Phone call permission is required to make calls.",
                    Toast.LENGTH_LONG
                ).show()
            }
            requestPhonePermissionLauncher.launch(Manifest.permission.CALL_PHONE)
        }
    }

    private fun makePhoneCall() {
        Toast.makeText(this, "Calling....", Toast.LENGTH_LONG).show()
    }
}

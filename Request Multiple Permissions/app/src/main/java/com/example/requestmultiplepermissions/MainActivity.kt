package com.example.requestmultiplepermissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        requestAllPermissions(this, this, 1)
        replaceWithFragment(HomeFragment())
        Perm.requestPermission(this, this)
//        val list = mutableListOf(
//            Manifest.permission.INTERNET,
//            Manifest.permission.CALL_PHONE,
//            Manifest.permission.READ_CONTACTS,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.SEND_SMS)
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
//            list.add(Manifest.permission.POST_NOTIFICATIONS)
//        }
//        val permissions = list.toTypedArray()
////
////        val btn = findViewById<Button>(R.id.button)
////
//            if(!hasPermission(this, permissions)){
//                Log.d("permissionReq", "Activ: $this ")
//                ActivityCompat.requestPermissions(this, permissions, 1)
//            }
    }

    private fun hasPermission(context: Context, permissions: Array<String>): Boolean {
        for (permission in permissions){
            if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }
    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
    private fun requestAllPermissions(context: Context, activity: Activity, requestCode: Int) {
        val list = mutableListOf(
            Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.SEND_SMS
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            list.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        val permissions = list.toTypedArray()

        // Check if any permission is not granted
        var permissionsToRequest = false
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest = true
                break
            }
        }

        // If any permission is not granted, request permissions
        if (permissionsToRequest) {
            Log.d("permissionReq", "Entered")
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }
    }

}
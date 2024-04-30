package com.example.requestmultiplepermissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat

object Perm {
    fun requestPermission(activity: Activity, context: Context){
        val list = mutableListOf(
            Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.SEND_SMS)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            list.add(Manifest.permission.POST_NOTIFICATIONS)
        }
        val permissions = list.toTypedArray()
//
//        val btn = findViewById<Button>(R.id.button)
//
        if(!hasPermission(context, permissions)){
            Log.d("permissionReq", "Activ: $this ")
            ActivityCompat.requestPermissions(activity, permissions, 1)
        }
    }

    private fun hasPermission(context: Context, permissions: Array<String>): Boolean {
        for (permission in permissions){
            if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }
}
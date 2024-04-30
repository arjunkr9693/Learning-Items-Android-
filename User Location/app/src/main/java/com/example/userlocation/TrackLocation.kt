package com.example.userlocation

import android.Manifest
import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.firebase.database.FirebaseDatabase

class TrackLocation : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var handler: Handler
    private lateinit var notificationManager: NotificationManager

    companion object {
        private const val TAG = "TrackLocation"
        private const val NOTIFICATION_ID = 1234
        private const val CHANNEL_ID = "LocationForegroundServiceChannel"
        private const val STOP_SERVICE_DELAY_MS = 120000L // 2 minutes
    }
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("Users")
    val phoneNumber = "1"





//    private fun updateFirebase(phoneNumber: String, key1Value: String, key2Value: String) {
//
//        // Get a reference to the specific node using the unique key (phone number)
//        val phoneNumberRef = reference.child(phoneNumber)
//
//        // Update the values associated with that key
//        val updates = hashMapOf(
//            "key1" to key1Value,
//            "key2" to key2Value
//            // Add more key-value pairs as needed
//        )
//
//        phoneNumberRef.updateChildren(updates as Map<String, Any>)
//            .addOnSuccessListener {
//                // Handle success
//                Log.d("FirebaseUpdateService", "Data updated successfully.")
//            }
//            .addOnFailureListener { e ->
//                // Handle failure
//                Log.e("FirebaseUpdateService", "Data could not be updated: ${e.message}")
//            }
//    }


    @SuppressLint("ForegroundServiceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()


//        reference.child(phoneNumber).setValue(LocationData("111", "789", true)).addOnSuccessListener {
//            Toast.makeText(this@TrackLocation, "Updated", Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener{
//            Toast.makeText(this@TrackLocation, "Not Updated", Toast.LENGTH_SHORT).show()
//        }

        Log.d(TAG, "Service created")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        createLocationRequest()
        createLocationCallback()

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = createNotification() // Create the notification before starting the service
        startForeground(NOTIFICATION_ID, notification) // Start the service with the pre-created notification

        handler = Handler()
        handler.postDelayed({ stopSelf() }, STOP_SERVICE_DELAY_MS)
        requestLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service destroyed")
        stopLocationUpdates()
        // Cancel the notification when service is destroyed
        notificationManager.cancel(NOTIFICATION_ID)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    private fun createLocationRequest(): LocationRequest {
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
            .build()

        return locationRequest
    }


    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    Log.d(TAG, "Latitude: ${location.latitude}, Longitude: ${location.longitude}")
                    // Handle location updates here
                    reference.child(phoneNumber).setValue(LocationData(location.latitude, location.longitude, true)).addOnSuccessListener {
                        Toast.makeText(this@TrackLocation, "Updated", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener{
                        Toast.makeText(this@TrackLocation, "Not Updated", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(): Notification {
        val notificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else {
            null
        }

        val notificationBuilder = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Location Tracking")
            .setContentText("Tracking your location in the background")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)  // Make the notification persistent
            .setPriority(Notification.PRIORITY_HIGH)  // Set notification priority

        notificationChannel?.let {
            notificationBuilder.setChannelId(CHANNEL_ID)
        }

        return notificationBuilder.build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        val channelName = "Location Service"
        val channel = NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
            description = "Foreground service to fetch location updates"
        }
        notificationManager.createNotificationChannel(channel)
        return channel
    }
}

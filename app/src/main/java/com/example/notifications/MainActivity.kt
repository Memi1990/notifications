package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.app.NotificationManagerCompat.IMPORTANCE_DEFAULT
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b:ActivityMainBinding
    public val CHANNEL_ID = "channel_id"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
    fun myToastNormal(view: View){
        Toast.makeText(this@MainActivity, "Normal", Toast.LENGTH_SHORT).show()
    }
    fun myToastGravity (view: View){
        val t = Toast.makeText(this@MainActivity, "Gravity", Toast.LENGTH_SHORT)
        t.setGravity(Gravity.CENTER or Gravity.LEFT, 0, 0)
        t.show()
    }
    fun myToastLayout(view: View){
        val layout = layoutInflater.inflate(R.layout.toast, null)
        layout!!.findViewById<TextView>(R.id.tvToastTitle).text = "ALERTA"
        layout!!.findViewById<TextView>(R.id.tvToastDescription).text = "Ésto es un Toast Personalizado"
        val t = Toast(this@MainActivity)
        t.duration = Toast.LENGTH_SHORT
        t.view = layout
        t.show()
    }
    fun notifStateBar(view: View){
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.icono_notificacion)
            .setContentTitle("Hola mindo!")
            .setContentText("Mi ejemplo de notificación")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val intent = Intent (this, ActivityResoult::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        val notif_ID = 1
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notif_ID, builder.build())
    }
    private fun createNotificationChannel (){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.channel_name)
            val importance = NotificationChannel.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = getString(R.string.channel_description)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}
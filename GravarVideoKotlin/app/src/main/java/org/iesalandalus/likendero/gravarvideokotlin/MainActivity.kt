package org.iesalandalus.likendero.gravarvideokotlin

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Button
import android.widget.MediaController
import java.security.Permission
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // comprovar permisos
        if(
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
        ){

        }
    }
    val ACTION_VIDEO_CAPTURE: Int = 1
    fun grabarOnclick(view:View){

        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also{
            videoIntent ->
            videoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(videoIntent,ACTION_VIDEO_CAPTURE)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // captura resultado de la camara
        if(requestCode == ACTION_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK && data != null){
            val uri: Uri = data.data

        }
    }
}

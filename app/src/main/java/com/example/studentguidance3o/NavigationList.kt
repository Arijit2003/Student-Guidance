package com.example.studentguidance3o

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class NavigationList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_list)
        var academicToBh1:LinearLayout=findViewById(R.id.AB_t_bh1)
        var academicToBh2:LinearLayout=findViewById(R.id.AB_t_bh2)
        var academicToBh3:LinearLayout=findViewById(R.id.AB_t_bh3)
        var academicTogh:LinearLayout=findViewById(R.id.AB_t_gh)
        var academicToUb:LinearLayout=findViewById(R.id.AB_t_ub)
        var academicToVm:LinearLayout=findViewById(R.id.AB_t_vm)
        var academicToLc:LinearLayout=findViewById(R.id.AB_t_lc)
        var academicToMPH:LinearLayout=findViewById(R.id.AB_t_mph)
        var academicToAudi:LinearLayout=findViewById(R.id.AB_t_audi)
        val parkingA:LinearLayout=findViewById(R.id.AB_t_parkingA)
        val postOff:LinearLayout=findViewById(R.id.AB_t_postOffice)
        academicToBh1.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.075293,76.859885")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        academicToBh2.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"New Vit Bhopal Boys Hostel")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        academicToBh3.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"New Vit Bhopal Boys Hostel")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        academicTogh.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.075020,76.852552")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        academicToUb.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.077430,76.850581")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        academicToVm.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.0773617,76.8509223")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        academicToLc.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.0783234,76.8504043")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        academicToMPH.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.076180,76.849838")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        academicToAudi.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.077920,76.851285")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        parkingA.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.0746557,76.8503436")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        postOff.setOnClickListener(){
            var uri: Uri =Uri.parse("https://www.google.co.in/maps/dir/"+"23.0774280,76.8514190"+"/"+"23.076110,76.849938")
            val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }
}
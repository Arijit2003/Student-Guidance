package com.example.studentguidance3o

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        val whatsapp:LinearLayout=findViewById(R.id.whatsapp)
        val telegram:LinearLayout=findViewById(R.id.telegram)
        val instagram:LinearLayout=findViewById(R.id.instagram)
        val toolbar:Toolbar=findViewById(R.id.toolbar)
        val imageSlider:ImageSlider=findViewById(R.id.imageSlider)
        val imageList:ArrayList<SlideModel> = arrayListOf()
        imageList.add(SlideModel(R.drawable.arijit,"Arijit Modak"))
        imageList.add(SlideModel(R.drawable.braj,"Braj Kishor  Sharma"))
        imageList.add(SlideModel(R.drawable.aswin,"Aswin P Kumar"))
        imageList.add(SlideModel(R.drawable.pradeep,"Pradeep T Reddy"))
        imageList.add(SlideModel(R.drawable.khilraj,"Khilraj Solanki"))
        imageSlider.setImageList(imageList,ScaleTypes.FIT)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        whatsapp.setOnClickListener(){
            val bool:Boolean=checkPackage("com.whatsapp")
            if(bool==true) {
                val uri: Uri = Uri.parse("https://chat.whatsapp.com/I4ZAsWPpzVH6Z4raZ0borX")
                val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            else{
                val uri:Uri=Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                startActivity(intent)
            }
        }
        telegram.setOnClickListener(){
            val bool:Boolean=checkPackage("org.telegram.messenger")
            if(bool==true) {
                val uri: Uri = Uri.parse("https://t.me/C_Revolution56477")
                val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            else{
                val uri:Uri=Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")
                val intent:Intent=Intent(Intent.ACTION_VIEW,uri)
                startActivity(intent)
            }
        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                onBackPressed()
            }
        }
        return true
    }


    private fun checkPackage(name:String):Boolean{
        var available=true
        try {
            packageManager.getPackageInfo(name,0)
        }catch (e: PackageManager.NameNotFoundException){
            available=false
        }
        return available
    }


}


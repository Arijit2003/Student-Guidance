package com.example.studentguidance3o

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.w3c.dom.Text

class FacultyInfo : AppCompatActivity() {
    lateinit var drawerLayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_info)
        drawerLayout=findViewById(R.id.drawerLayout3)
        val toolbar:Toolbar=findViewById(R.id.toolbar3)
        var img:ImageView=findViewById(R.id.img)
        var name:TextView=findViewById(R.id.textView2)
        var level:TextView=findViewById(R.id.textView62)
        var nextname:TextView=findViewById(R.id.textView68)
        var department:TextView=findViewById(R.id.textView69)
        var cabin:TextView=findViewById(R.id.textView70)
        var mailID:TextView=findViewById(R.id.textView71)

        var bundle:Bundle=intent.extras!!
        var imageRes:Int=bundle.getInt("image")

        img.setImageResource(imageRes)
        name.text=bundle.getString("name")
        level.text=bundle.getString("level")
        nextname.text=bundle.getString("name")
        department.text=bundle.getString("department")
        cabin.text=bundle.getString("cabin")
        mailID.text=bundle.getString("mailID")
        /*with(navView) {

            img.setImageResource(imageRes)
            name.text=bundle.getString("name")
            level.text=bundle.getString("level")
            nextname.text=bundle.getString("name")
            department.text=bundle.getString("department")
            cabin.text=bundle.getString("cabin")
            mailID.text=bundle.getString("mailID")

        }*/
        setSupportActionBar(toolbar)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                onBackPressed()
            }
        }
        return true
    }
}



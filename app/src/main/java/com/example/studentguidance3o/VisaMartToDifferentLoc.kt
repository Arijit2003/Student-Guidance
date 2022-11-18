package com.example.studentguidance3o

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class VisaMartToDifferentLoc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visa_mart_to_different_loc)
        var listview: ListView =findViewById(R.id.visamartToDifferentLoc)
        val dest="Destination"
        val source="23.0773617,76.8509223"
        //creating arraylist
        var arrayList:ArrayList<Location> = ArrayList()
        arrayList.add(Location(R.drawable.academic,"Academic Block",dest))
        arrayList.add(Location(R.drawable.boys_hostel1,"Boys Hostel 1",dest))
        arrayList.add(Location(R.drawable.boys_hostel2,"Boys Hostel 2",dest))
        arrayList.add(Location(R.drawable.boys_hostel3,"Boys Hostel 3",dest))
        arrayList.add(Location(R.drawable.girls_hostel,"Girls Hostel",dest))
        arrayList.add(Location(R.drawable.underbelly,"Underbelly",dest))
        arrayList.add(Location(R.drawable.lab_complex,"Lab Complex",dest))
        arrayList.add(Location(R.drawable.multipurpose,"Multipurpose Hall",dest))
        arrayList.add(Location(R.drawable.auditorium,"Auditorium",dest))
        arrayList.add(Location(R.drawable.parking,"Parking Area",dest))
        arrayList.add(Location(R.drawable.post_office,"Post Office",dest))
        var arrayAdapter=MyArrayAdapter(this,R.layout.source_page_one_sample,arrayList)
        listview.adapter=arrayAdapter
        listview.setOnItemClickListener { parent, view, position, id ->
            var item:Location=listview.getItemAtPosition(position) as Location
            when(item.locN){
                "Academic Block"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0774280,76.8514190")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Boys Hostel 1"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075293,76.859885")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Boys Hostel 2"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Boys Hostel 3"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"New Vit Bhopal Boys Hostel")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Girls Hostel"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.075020,76.852552")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Underbelly"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077430,76.850581")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Lab Complex"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0783234,76.8504043")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Multipurpose Hall"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076180,76.849838")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Auditorium"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.077920,76.851285")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Parking Area"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0746557,76.8503436")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                "Post Office"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.076110,76.849938")
                    val intent: Intent = Intent(Intent.ACTION_VIEW,uri)
                    intent.setPackage("com.google.android.apps.maps")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }

            }
        }
    }
}
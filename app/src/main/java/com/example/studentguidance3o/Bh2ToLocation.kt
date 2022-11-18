package com.example.studentguidance3o

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class Bh2ToLocation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bh2_to_location)
        var listView:ListView=findViewById(R.id.Bh2asSource)
        var DestinationName:ArrayList<Location> = ArrayList()
        val dest="Destination"
        DestinationName.add(Location(R.drawable.academic,"Academic BLock",dest))
        DestinationName.add(Location(R.drawable.boys_hostel1,"Boys Hostel 1",dest))
        DestinationName.add(Location(R.drawable.boys_hostel3,"Boys Hostel 3",dest))
        DestinationName.add(Location(R.drawable.girls_hostel,"Girls Hostel",dest))
        DestinationName.add(Location(R.drawable.underbelly,"Underbelly",dest))
        DestinationName.add(Location(R.drawable.underbelly,"Visa Mart",dest))
        DestinationName.add(Location(R.drawable.lab_complex,"Lab Complex",dest))


        DestinationName.add(Location(R.drawable.multipurpose,"Multipurpose Hall",dest))
        DestinationName.add(Location(R.drawable.auditorium,"Auditorium",dest))
        DestinationName.add(Location(R.drawable.parking,"Parking Area",dest))

        DestinationName.add(Location(R.drawable.post_office,"Post Office",dest))
        // creating array adapter
        var location_array_adapter=MyArrayAdapter(this,R.layout.source_page_one_sample,DestinationName)
        val source="New Vit Bhopal Boys Hostel"
        listView.adapter=location_array_adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            var item:Location=listView.getItemAtPosition(position) as Location
            when(item.locN){
                "Academic BLock"->{
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
                "Visa Mart"->{
                    var uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+"23.0773617,76.8509223")
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
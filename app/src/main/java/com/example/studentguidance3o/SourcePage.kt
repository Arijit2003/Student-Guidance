package com.example.studentguidance3o

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class SourcePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_page)
        val listView:ListView=findViewById(R.id.listview)
        // Creating an arraylist
        var arrayListLocation:ArrayList<Location> = ArrayList()
        arrayListLocation.add(Location(R.drawable.academic,"Academic Block","Source"))
        arrayListLocation.add(Location(R.drawable.boys_hostel1,"Boys Hostel 1","Source"))
        arrayListLocation.add(Location(R.drawable.boys_hostel2,"Boys Hostel 2","Source"))
        arrayListLocation.add(Location(R.drawable.boys_hostel3,"Boys Hostel 3","Source"))
        arrayListLocation.add(Location(R.drawable.girls_hostel,"Girls Hostel","Source"))
        arrayListLocation.add(Location(R.drawable.underbelly,"Underbelly","Source"))
        arrayListLocation.add(Location(R.drawable.underbelly,"Visa Mart","Source"))
        arrayListLocation.add(Location(R.drawable.lab_complex,"Lab Complex","Source"))
        arrayListLocation.add(Location(R.drawable.multipurpose,"Multipurpose Hall","Source"))
        arrayListLocation.add(Location(R.drawable.auditorium,"Auditorium","Source"))
        arrayListLocation.add(Location(R.drawable.parking,"Parking Area","Source"))
        arrayListLocation.add(Location(R.drawable.post_office,"Post Office","Source"))
        var LocArrayAdapter = MyArrayAdapter(this,R.layout.source_page_one_sample,arrayListLocation)
        listView.adapter=LocArrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            var item:Location= listView.getItemAtPosition(position) as Location
            Toast.makeText(this,"${item.locN}",Toast.LENGTH_LONG).show()
            when(item.locN){
                "Academic Block"->{
                    val intent: Intent = Intent(this,NavigationList::class.java)
                    startActivity(intent)
                }
                "Boys Hostel 1"->{
                    val intent:Intent=Intent(this,Bh1ToLocation::class.java)
                    startActivity(intent)
                }
                "Boys Hostel 2"->{
                    val intent:Intent=Intent(this,Bh2ToLocation::class.java)
                    startActivity(intent)
                }
                "Boys Hostel 3"->{
                    val intent:Intent=Intent(this,Bh3ToLocation::class.java)
                    startActivity(intent)
                }
                "Girls Hostel"->{
                    val intent:Intent=Intent(this,GirlsHostelToDiffLoc::class.java)
                    startActivity(intent)
                }
                "Underbelly"->{
                    val intent:Intent=Intent(this,UnderbellyToDifferentLoc::class.java)
                    startActivity(intent)
                }
                "Visa Mart"->{
                    val intent:Intent=Intent(this,VisaMartToDifferentLoc::class.java)
                    startActivity(intent)
                }
                "Lab Complex"->{
                    val intent:Intent=Intent(this,LabComplexToDifferentLoc::class.java)
                    startActivity(intent)
                }
                "Multipurpose Hall"->{
                    val intent:Intent=Intent(this,MultipurposeToDifferentLoc::class.java)
                    startActivity(intent)
                }
                "Auditorium"->{
                    val intent:Intent=Intent(this,AuditoriumToDifferentLoc::class.java)
                    startActivity(intent)
                }
                "Parking Area"->{
                    val intent:Intent=Intent(this,ParkingAreaToDifferentLoc::class.java)
                    startActivity(intent)
                }
                "Post Office"->{
                    val intent:Intent=Intent(this,PostOfficeToDifferentLoc::class.java)
                    startActivity(intent)
                }

            }
        }
    }
}
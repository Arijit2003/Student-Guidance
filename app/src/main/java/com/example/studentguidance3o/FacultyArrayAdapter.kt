package com.example.studentguidance3o

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text


class FacultyArrayAdapter(var content:Context, var resource:Int,var FacultyList:ArrayList<Faculty>):ArrayAdapter<Faculty>(content,resource,FacultyList){
    override fun getView(position:Int,convertView: View?,parent:ViewGroup):View{
        var layoutInflater:LayoutInflater=LayoutInflater.from(content)
        var view:View=layoutInflater.inflate(resource,null)
        var faculty:Faculty=FacultyList[position]
        var image:ImageView=view.findViewById(R.id.ashish_t)
        var name:TextView=view.findViewById(R.id.AshishTripathi)
        var level:TextView=view.findViewById(R.id.textView4)
        image.setImageResource(faculty.img)
        name.text=faculty.name
        level.text=faculty.level
        return view
    }
}
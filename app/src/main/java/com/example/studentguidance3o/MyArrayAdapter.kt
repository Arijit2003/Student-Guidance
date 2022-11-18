package com.example.studentguidance3o


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyArrayAdapter(var content:Context,val resource:Int,var locationList:ArrayList<Location>):ArrayAdapter<Location>(content,resource,locationList){
    override fun getView(position:Int, convertView: View?, parent:ViewGroup):View {
        var layoutInflater:LayoutInflater=LayoutInflater.from(content)
        var view:View=layoutInflater.inflate(resource,null)
        var imageView:ImageView=view.findViewById(R.id.locImage)
        var source:TextView=view.findViewById(R.id.textView64)
        var loc:TextView=view.findViewById(R.id.textView65)
        var location:Location=locationList[position]
        imageView.setImageResource(location.img)
        source.text=location.locN
        loc.text=location.location
        return view
    }
}
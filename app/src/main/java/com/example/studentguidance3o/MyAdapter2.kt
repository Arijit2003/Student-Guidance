package com.example.studentguidance3o

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter2(var context: Context, var facultyList: ArrayList<Faculty>, var delete: MenuItem) :
    RecyclerView.Adapter<MyAdapter2.ViewHolder>() {
    var activateSelection = false
    var updatedList = ArrayList<Faculty>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        Glide.with(context)
            .load(facultyList[position].image)
            .into(holder.FacultyImage)
        holder.FacultyName.text = facultyList[position].name
        holder.FacultyDesignation.setText(facultyList[position].designation)
        holder.linearLayout.setOnClickListener {
            if (activateSelection) {
                if (facultyList[position].isSelected) {
                    holder.linearLayout.setBackgroundColor(android.R.attr.selectableItemBackground)
                    facultyList[position].isSelected = false
                    updatedList.remove(facultyList[position])
                    if (updatedList.size == 0) {
                        activateSelection = false
                        delete.isVisible = false
                    }
                } else {
                    facultyList[position].isSelected = true
                    holder.linearLayout.setBackgroundColor(Color.LTGRAY)
                    updatedList.add(facultyList[position])
                }
            } else {
                val intent = Intent(context, Profile::class.java)
                intent.putExtra("facImage", facultyList[position].image.toString())
                    .putExtra("facName", facultyList[position].name)
                    .putExtra("facDesignation", facultyList[position].designation)
                    .putExtra("facCabin", facultyList[position].cabinNo)
                    .putExtra("facEmail", facultyList[position].email)
                    .putExtra("facDepartment", facultyList[position].department)
                    .putExtra("class", "MyAdapter2")
                ContextCompat.startActivity(context, intent, null)
            }
        }
        //*****
        holder.linearLayout.setOnLongClickListener {
            activateSelection = true
            delete.isVisible = true
            holder.linearLayout.setBackgroundColor(Color.LTGRAY)
            facultyList[position].isSelected = true
            updatedList.add(facultyList[position])
            true
        }
    }

    override fun getItemCount(): Int {
        return facultyList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var FacultyImage: ImageView
        var FacultyName: TextView
        var FacultyDesignation: TextView
        var linearLayout: LinearLayout

        init {
            FacultyImage = itemView.findViewById(R.id.FacultyImage)
            FacultyName = itemView.findViewById(R.id.FacultyName)
            FacultyDesignation = itemView.findViewById(R.id.FacultyDesignation)
            linearLayout = itemView.findViewById(R.id.linearLayout)
        }
    }
}

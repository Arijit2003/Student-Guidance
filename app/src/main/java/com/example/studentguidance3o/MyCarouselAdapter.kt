package com.example.studentguidance3o

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView

class MyCarouselAdapter (val context: Context, val arrayList: ArrayList<Model>) : RecyclerView.Adapter<MyCarouselAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.item_latout_carousel, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTv.setText(arrayList[position].title)
        holder.imageView.setImageResource(arrayList[position].image)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ReflectionImageView
        var titleTv: TextView

        init {
            imageView = itemView.findViewById(R.id.imageView)
            titleTv = itemView.findViewById(R.id.title)
        }
    }


}

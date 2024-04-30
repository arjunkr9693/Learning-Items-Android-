package com.example.apiproject

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Activity, val productList : List<Product>) :
RecyclerView.Adapter<MyAdapter.ViewHolder>()
{
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail = itemView.findViewById<ShapeableImageView>(R.id.thumbnail)
        val headingTitle = itemView.findViewById<TextView>(R.id.headingTitle)
        val star1 = itemView.findViewById<ImageView>(R.id.star1)
        val star2 = itemView.findViewById<ImageView>(R.id.star2)
        val star3 = itemView.findViewById<ImageView>(R.id.star3)
        val star4 = itemView.findViewById<ImageView>(R.id.star4)
        val star5 = itemView.findViewById<ImageView>(R.id.star5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.headingTitle.text = productList[position].title.toString()
        Picasso.get().load(productList[position].thumbnail).into(holder.thumbnail)
        val rating = productList[position].rating.toInt() // Convert rating to an integer
        val maxRating = 5

        // Loop through all star ImageViews
        for (i in 1..maxRating) {
            val starImageView = when (i) {
                1 -> holder.star1
                2 -> holder.star2
                3 -> holder.star3
                4 -> holder.star4
                5 -> holder.star5
                else -> null
            }
            // Set alpha based on the rating
            starImageView?.alpha = if (i <= rating) 1.0f else 0.0f
        }
    }


}
package com.example.simplelistview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.makeramen.roundedimageview.RoundedImageView
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(private var context: Activity, var arrayList: ArrayList<User>) : ArrayAdapter<User>(context, R.layout.listview_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.listview_item, parent, false)
        }

        val name = view!!.findViewById<TextView>(R.id.nameTextView)
        val lastMsg = view.findViewById<TextView>(R.id.lastMessageTextView)
        val lastTime = view.findViewById<TextView>(R.id.lastMessageTimeTextView)
        val imgId = view.findViewById<CircleImageView>(R.id.imageView1)

        name.text = arrayList[position].name
        lastMsg.text = arrayList[position].lastMsg
        lastTime.text = arrayList[position].lastTime
        imgId.setImageResource(arrayList[position].img)

        return view
    }
}

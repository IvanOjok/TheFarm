package com.softbox.thefarm.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softbox.thefarm.ActivityDetails
import com.softbox.thefarm.DiagnosisActivity
import com.softbox.thefarm.R

class ActivityAdapter(r: Context, options: ArrayList<Activity>) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder >() {
    var c = r
    var y = options
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.booking_list, parent, false)
        return ActivityViewHolder(inflate)
    }

    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(context: Context, name:String, bby:String, dd:String, date:String) {

            val n = itemView.findViewById<TextView>(R.id.name)
            n.text = name

            val d = itemView.findViewById<TextView>(R.id.date)
            d.text = date

            val by = itemView.findViewById<TextView>(R.id.by)
            by.text = bby

            val on = itemView.findViewById<TextView>(R.id.on)
            on.text = dd

            val imageView3 = itemView.findViewById<ImageView>(R.id.imageView3)
            imageView3.setImageResource(R.drawable.active)

        }

    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        Log.d("y", "$y")
        Log.d("c", "$c")
        holder.onBind(c, "ID: ${y[position].calfID!!}", "Type: ${y[position].type}", "On:  ${y[position].aDate}" +
                "${y[position].aTime}", "Recorded By: ${y[position].createdBy}")

        holder.itemView.setOnClickListener {
            val intent = Intent(c, ActivityDetails::class.java)
            intent.putExtra("calfID", y[position].calfID)
            intent.putExtra("type", y[position].type)
            intent.putExtra("detail", y[position].detail)
            intent.putExtra("result", y[position].result)
            intent.putExtra("date", y[position].aDate)
            intent.putExtra("time", y[position].aTime)
            intent.putExtra("owner", y[position].createdBy)

            c.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return y.size
    }
}
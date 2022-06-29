package com.softbox.thefarm.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softbox.thefarm.FinalReservationActivity
import com.softbox.thefarm.R

class StockAdapter(r: Context, options: ArrayList<Stock>) : RecyclerView.Adapter<StockAdapter.bookingViewHolder>(){
    var c=r
    var y = options
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): bookingViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.booking_list, parent, false)
        return bookingViewHolder(inflate)
    }

    class bookingViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(context: Context, name:String, bby:String, dd:String, date:String){

            val n = itemView.findViewById<TextView>(R.id.name)
            n.text = name

            val d = itemView.findViewById<TextView>(R.id.date)
            d.text = date

            val by = itemView.findViewById<TextView>(R.id.by)
            by.text = bby

            val on = itemView.findViewById<TextView>(R.id.on)
            on.text = dd
        }

    }

    override fun onBindViewHolder(holder: bookingViewHolder, position: Int) {
        Log.d("y", "$y")
        Log.d("c", "$c")
        holder.onBind(c, "ID: ${y[position].calfID!!}", "By: ${y[position].owner}", "On:  ${y[position].date}" +
                "${y[position].time}", "Registered By: ${y[position].upload}")

        holder.itemView.setOnClickListener {
            val intent = Intent(c, FinalReservationActivity::class.java)
            intent.putExtra("calfID", y[position].calfID )
            intent.putExtra("calfAge", y[position].calfAge)
            intent.putExtra("calfWeight", y[position].calfWeight)
            intent.putExtra("calfDetails", y[position].calfDetails )
            intent.putExtra("date", y[position].date)
            intent.putExtra("time", y[position].time)
            intent.putExtra("owner", y[position].owner)
            intent.putExtra("upload", y[position].upload)
            c.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return y.size
    }
}
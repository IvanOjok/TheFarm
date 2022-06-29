package com.softbox.thefarm.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softbox.thefarm.DiagnosisActivity
import com.softbox.thefarm.FinalReservationActivity
import com.softbox.thefarm.R

class CalvesAdapter(r: Context, options: ArrayList<Stock>) : RecyclerView.Adapter<CalvesAdapter.stockViewHolder >(){
    var c=r
    var y = options
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): stockViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.to_message_layout, parent, false)
        return stockViewHolder(inflate)
    }

    class stockViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(context: Context, name:String){

            val n = itemView.findViewById<TextView>(R.id.name)
            n.text = name

        }

    }

    override fun onBindViewHolder(holder: stockViewHolder, position: Int) {
        Log.d("y", "$y")
        Log.d("c", "$c")
        holder.onBind(c, y[position].calfID!!)

        holder.itemView.setOnClickListener {
            val intent = Intent(c, DiagnosisActivity::class.java)
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
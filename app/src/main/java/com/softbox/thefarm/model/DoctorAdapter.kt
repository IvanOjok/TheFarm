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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.softbox.thefarm.DoctorInfo
import com.softbox.thefarm.R

class DoctorAdapter(r:Context, options: ArrayList<Employee>) : RecyclerView.Adapter<DoctorAdapter.doctorViewHolder>(){
    var c=r
    var y = options
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): doctorViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.doctors_list, parent, false)
        return doctorViewHolder(inflate)
    }

    class doctorViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(context: Context, name:String, id:String){

            val i = itemView.findViewById<ImageView>(R.id.imageView3)

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_account_circle_24)

//            Glide.with(context).load(image).apply(options).into(i)
            val n = itemView.findViewById<TextView>(R.id.name)
            n.text = name

            val idd = itemView.findViewById<TextView>(R.id.id)
            idd.text = id
        }

    }

    override fun onBindViewHolder(holder: doctorViewHolder, position: Int) {
        Log.d("y", "$y")
        Log.d("c", "$c")
        holder.onBind(c, y[position].firstName!! + y[position].lastName!!, "ID: ${y[position].empID}")

        holder.itemView.setOnClickListener {
            val intent = Intent(c, DoctorInfo::class.java)
            intent.putExtra("firstName", y[position].firstName)
            intent.putExtra("lastName", y[position].lastName)
            intent.putExtra("staffId", y[position].empID)

            intent.putExtra("type", y[position].type)
//            intent.putExtra("email", y[position].email)
//            intent.putExtra("prof", y[position].profession)
//            intent.putExtra("idNo", y[position].doctorNo)
            c.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return y.size
    }
}
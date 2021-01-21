package com.example.assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class LanguageAdapter(val context:Context, val list: ArrayList<LanguageData.Items>): RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

      class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var fullName:TextView=view.findViewById(R.id.full_nameLA)
        var owner:TextView=view.findViewById(R.id.ownerLA)
        var description:TextView=view.findViewById(R.id.descriptionLA)
          var layout:ConstraintLayout=view.findViewById(R.id.layoutLA)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.language_adapter_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list[position]
        holder.fullName.text=item.fullName
        holder.owner.text=item.owner.login
        holder.description.text=item.description
        holder.layout.setOnClickListener{
            Toast.makeText(context,"Here",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}
package com.example.recipieapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PopularMyAdapter(var context:Activity,var recipeList:ArrayList<Recipe>):RecyclerView.Adapter<PopularMyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rHeading=itemView.findViewById<TextView>(R.id.popularText)
        val rTime=itemView.findViewById<TextView>(R.id.popularTime)
        val rImg=itemView.findViewById<ImageView>(R.id.popularImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.each_item_of_rv,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem=recipeList[position]
        holder.rHeading.text=currItem.tittle
        Picasso.get().load(currItem.img).into(holder.rImg);
        var time=currItem.ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()  //ing is splitted a/q to \n dropLastWhile removes any empty ele b/w two spaces and typedArray convert in array whose [0] gives time
        holder.rTime.text= time[0]

        holder.itemView.setOnClickListener{   //anywhere clicked opens recipeActivity
            val intent= Intent(context,RecipieActivity::class.java)
            intent.putExtra("image",currItem.img)
            intent.putExtra("title",currItem.tittle)
            intent.putExtra("ingredients",currItem.ing)
            intent.putExtra("steps",currItem.des)
            intent.putExtra("time",time[0])
            context.startActivity(intent)
        }
    }

}
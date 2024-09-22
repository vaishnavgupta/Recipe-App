package com.example.recipieapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipieapp.PopularMyAdapter.MyViewHolder
import com.squareup.picasso.Picasso

class SearchMyAdapter(val context:Activity, var recipeList:ArrayList<Recipe>): RecyclerView.Adapter<SearchMyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val foodImg=itemView.findViewById<ImageView>(R.id.mealImg)
        val foodTitle=itemView.findViewById<TextView>(R.id.mealTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.eachiteminsearchrv,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem=recipeList[position]
        holder.foodTitle.text=currItem.tittle
        Picasso.get().load(currItem.img).into(holder.foodImg);

        var time=currItem.ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        holder.itemView.setOnClickListener{   //anywhere clicked opens recipeActivity
            val intent= Intent(context,RecipieActivity::class.java)
            intent.putExtra("image",currItem.img)
            intent.putExtra("title",currItem.tittle)
            intent.putExtra("ingredients",currItem.ing)
            intent.putExtra("steps",currItem.des)
            intent.putExtra("time", time[0])
            context.startActivity(intent)
        }
    }



    //to show data a/q to typed data
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList:ArrayList<Recipe>){
        recipeList=filteredList
        notifyDataSetChanged()

    }
}
package com.example.recipieapp

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

class CategoryMyAdapter(var context:Activity,var recipeList:ArrayList<Recipe>) :RecyclerView.Adapter<CategoryMyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val detImg=itemView.findViewById<ImageView>(R.id.detailImg)
        val detTitle=itemView.findViewById<TextView>(R.id.detailTitle)
        val detTime=itemView.findViewById<TextView>(R.id.textView9)
        val detBck=itemView.findViewById<ImageView>(R.id.detailBck)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.each_item_in_cat_detail,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem=recipeList[position]
        holder.detTitle.text=currItem.tittle
        Picasso.get().load(currItem.img).into(holder.detImg);
        var time=currItem.ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()  //ing is splitted a/q to \n dropLastWhile removes any empty ele b/w two spaces and typedArray convert in array whose [0] gives time
        holder.detTime.text= time[0]

        //setting up click listener
        holder.detBck.setOnClickListener{
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
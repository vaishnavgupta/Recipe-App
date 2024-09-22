package com.example.recipieapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class HomeActivity : AppCompatActivity() {
    lateinit var popRecyclerView:RecyclerView
    lateinit var recipeData:ArrayList<Recipe>
    lateinit var myPopAdapter:PopularMyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        popRecyclerView=findViewById(R.id.rvPopular)
        recipeData=ArrayList()
        var roomDB=Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=roomDB.getDao()
        var recipies=daoObject.getAll()
        for(i in recipies!!.indices){
            if(recipies[i]!!.category.contains("Popular")){
                recipeData.add(recipies[i]!!)
            }
        }
        popRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        myPopAdapter=PopularMyAdapter(this,recipeData)
        popRecyclerView.adapter=myPopAdapter


        val searchBar=findViewById<EditText>(R.id.search)
        searchBar.setOnClickListener{
            val intent= Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

        //setting dialog for sede toggle
        val sdTgl=findViewById<ImageView>(R.id.sideToggle)
        sdTgl.setOnClickListener {
            val dialog=Dialog(this)
            dialog.setContentView(R.layout.bottom_navigation_sheet)
            dialog.show()
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setGravity(Gravity.BOTTOM)
        }


        //setting clicking calls of categries
        val sld=findViewById<ImageView>(R.id.salad)
        val mnCrs=findViewById<ImageView>(R.id.drink)
        val drks=findViewById<ImageView>(R.id.drinksReal)
        val desrt=findViewById<ImageView>(R.id.desserts)

        sld.setOnClickListener {
            val intent = Intent(this, ComponentActivity::class.java)
            intent.putExtra("category", "Salad")
            intent.putExtra("title", "Salad")
            startActivity(intent)
        }
        mnCrs.setOnClickListener {
            val intent = Intent(this, ComponentActivity::class.java)
            intent.putExtra("category", "Dish")
            intent.putExtra("title", "Main Dish")
            startActivity(intent)
        }
        drks.setOnClickListener {
            val intent = Intent(this, ComponentActivity::class.java)
            intent.putExtra("category", "Drinks")
            intent.putExtra("title", "Drinks")
            startActivity(intent)
        }
        desrt.setOnClickListener {
            val intent = Intent(this, ComponentActivity::class.java)
            intent.putExtra("category", "Desserts")
            intent.putExtra("title", "Desserts")
            startActivity(intent)
        }


    }
}
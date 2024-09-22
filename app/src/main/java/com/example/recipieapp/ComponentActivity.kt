package com.example.recipieapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class ComponentActivity : AppCompatActivity() {
    lateinit var catgRecyclerView: RecyclerView
    lateinit var recipeData:ArrayList<Recipe>
    lateinit var myCatAdapter: CategoryMyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)

        val tit=findViewById<TextView>(R.id.titleDetail)
        tit.text=intent.getStringExtra("title")
        setUpRecyclerView()

        val back=findViewById<ImageView>(R.id.imageView4)
        back.setOnClickListener {
            finish()
        }


    }
    fun setUpRecyclerView(){
        //recyclerview
        catgRecyclerView=findViewById(R.id.rvCtDetail)
        recipeData=ArrayList()
        var roomDB= Room.databaseBuilder(this@ComponentActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=roomDB.getDao()
        var recipies=daoObject.getAll()
        for(i in recipies!!.indices){
            if(recipies[i]!!.category.contains(intent.getStringExtra("category")!!)){
                recipeData.add(recipies[i]!!)
            }
        }
        catgRecyclerView.layoutManager= LinearLayoutManager(this)
        myCatAdapter=CategoryMyAdapter(this,recipeData)
        catgRecyclerView.adapter=myCatAdapter
    }
}
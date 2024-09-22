package com.example.recipieapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class SearchActivity : AppCompatActivity() {
    lateinit var srchRecyclerView: RecyclerView
    lateinit var recipeData:ArrayList<Recipe>
    lateinit var mySrchAdapter: SearchMyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        //go to home
        val goToHome=findViewById<ImageView>(R.id.goToHome)
        goToHome.setOnClickListener{
            finish()
        }

        //search bar
        val searchBar=findViewById<EditText>(R.id.searchET)
        searchBar.requestFocus()


        //Calling rv
        setUpRecyclerView()


        //changing text a/q to typed texrt in search bar
        searchBar.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString()!=""){
                    filterData(s.toString())
                }else{  //set rv in empty
                    setUpRecyclerView()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })



    }

    fun setUpRecyclerView(){
        //recyclerview
        srchRecyclerView=findViewById(R.id.rvSearch)
        recipeData=ArrayList()
        var roomDB= Room.databaseBuilder(this@SearchActivity,AppDatabase::class.java,"db_name")
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
        srchRecyclerView.layoutManager= LinearLayoutManager(this)
        mySrchAdapter=SearchMyAdapter(this,recipeData)
        srchRecyclerView.adapter=mySrchAdapter
    }

    private fun filterData(string: String) {
        var filterData=ArrayList<Recipe>()
        for(i in recipeData.indices){
            if(recipeData[i].tittle.lowercase().contains(string.lowercase())){
                filterData.add(recipeData[i])
            }
            mySrchAdapter.filterList(filterData)
        }
    }
}
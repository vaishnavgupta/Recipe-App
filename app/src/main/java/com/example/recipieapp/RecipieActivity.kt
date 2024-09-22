package com.example.recipieapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class RecipieActivity : AppCompatActivity() {

    var cropType=true   //showing img is centre cropped means default cond
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipie)

        //working on full scr btn
        val fLScr=findViewById<ImageView>(R.id.fullScr)
        val rcpsImgs=findViewById<ImageView>(R.id.rcpImage)
        fLScr.setOnClickListener {
            if(cropType){
                rcpsImgs.scaleType=ImageView.ScaleType.FIT_CENTER
                Picasso.get().load(intent.getStringExtra("image")).into(rcpsImgs);
                cropType=false
            }
            else{
                rcpsImgs.scaleType=ImageView.ScaleType.CENTER_CROP
                Picasso.get().load(intent.getStringExtra("image")).into(rcpsImgs);
                cropType=true
            }
        }

        //working of back button
        val bk=findViewById<ImageView>(R.id.backtohome)
        bk.setOnClickListener {
            finish()
        }

        //setting up the contents

        Picasso.get().load(intent.getStringExtra("image")).into(rcpsImgs);

        val t=findViewById<TextView>(R.id.rcpTitle)
        val dur=findViewById<TextView>(R.id.rcpTime)
        val ingBtn=findViewById<Button>(R.id.ingredientsBtn)
        val stpBtn=findViewById<Button>(R.id.stepsBtn)
        val ingData=findViewById<TextView>(R.id.rcpIngData)
        val stpData=findViewById<TextView>(R.id.rcpStepData)
        val ingSr=findViewById<ScrollView>(R.id.ingScroll)
        val stpSr=findViewById<ScrollView>(R.id.stepScroll)

        t.text=intent.getStringExtra("title")
        dur.text=intent.getStringExtra("time")
        ingData.text=intent.getStringExtra("ingredients")
        stpData.text=intent.getStringExtra("steps")

        //default
        stpBtn.background=null
        stpBtn.setTextColor(getColor(R.color.black))

        stpBtn.setOnClickListener {
            stpBtn.setBackgroundResource(R.drawable.btn_ing)
            stpBtn.setTextColor(getColor(R.color.white))
            ingBtn.setTextColor(getColor(R.color.black))
            ingBtn.background=null
            ingSr.visibility= View.GONE
            stpSr.visibility= View.VISIBLE


        }

        ingBtn.setOnClickListener {
            ingBtn.setBackgroundResource(R.drawable.btn_ing)
            ingBtn.setTextColor(getColor(R.color.white))
            stpBtn.setTextColor(getColor(R.color.black))
            stpBtn.background=null
            stpSr.visibility= View.GONE
            ingSr.visibility= View.VISIBLE


        }





    }
}
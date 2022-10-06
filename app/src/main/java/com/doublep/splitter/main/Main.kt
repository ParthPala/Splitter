package com.doublep.splitter.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.doublep.splitter.R
import com.doublep.splitter.customViews.TextViewQuestion
import com.doublep.splitter.customViews.TextViewRestOfAll

class Main : AppCompatActivity() {

    val nameList = arrayListOf<String>()
    lateinit var name_et: EditText
    lateinit var submit_btn: TextView
    lateinit var tv_namelist: TextView
    lateinit var tv_addBtn: TextViewQuestion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_details)
        initViews()
        setClicks()
    }

    private fun initViews() {
        name_et = findViewById(R.id.et_name)
        submit_btn = findViewById(R.id.tv_submit)
        tv_namelist = findViewById(R.id.tv_nameList)
        tv_addBtn = findViewById(R.id.iv_addBtn)
    }

    private fun setClicks() {
        tv_addBtn.setOnClickListener {
            if (name_et.text.trim().isNotEmpty()) {
                nameList.add(name_et.text.toString())
                tv_namelist.text = ""
                name_et.setText("")
                nameList.forEachIndexed { index, s ->
                    tv_namelist.append("\n ${index + 1}) $s")
                }
            }else
            {
                Toast.makeText(this,"Name cannot be empty.",Toast.LENGTH_LONG).show()
            }
        }

        submit_btn.setOnClickListener{
            if (nameList.size>1) {
                val intent = Intent(this, SplitDetails::class.java)
                intent.putStringArrayListExtra("data", nameList)
                startActivity(intent)
                finish()
            }else
                Toast.makeText(this,"Please enter at least 2 persons name.",Toast.LENGTH_LONG).show()
        }

    }



}
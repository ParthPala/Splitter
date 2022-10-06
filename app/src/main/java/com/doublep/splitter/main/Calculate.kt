package com.doublep.splitter.main

import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doublep.splitter.R
import com.doublep.splitter.data.CalculationDetailsData
import com.doublep.splitter.data.SplitDetailsData
import com.google.gson.Gson

class Calculate : AppCompatActivity() {

    lateinit var recyCalculations : RecyclerView
    private val namesList = ArrayList<String>()
    private val detailsList = ArrayList<String>()
    val dataList = ArrayList<CalculationDetailsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculation_page)
        val intent = intent
        val bundle = intent.extras
        val dataList = bundle?.getStringArrayList("dataList")
        val namesDataList = bundle?.getStringArrayList("nameList")
        detailsList.addAll(dataList ?: arrayListOf())
        namesList.addAll(namesDataList ?: arrayListOf())

        initViews()
        calculatePerPersonDetails()
    }

    private fun initViews(){
    recyCalculations = findViewById(R.id.rvsplitDetails)
    }

   private fun calculatePerPersonDetails()
    {
        namesList.forEach { name ->
            val obj = CalculationDetailsData(name, arrayListOf(),0)
            dataList.add(obj)
            }

        detailsList.forEach {
            val gson = Gson().fromJson(it.toString(),SplitDetailsData::class.java)
            dataList.forEach { map ->
                    if (gson.names.contains(map.name))
                    {   val perReasonAmt = gson.amount/gson.names.size
                                    val innerObj = CalculationDetailsData.AmountPerReason(gson.reason,perReasonAmt)
                                    map.reasons.add(innerObj)
                        map.totalAmount = (map.totalAmount+perReasonAmt)
                                }
                    }
                }

        setRecyAdapter()
    }

    fun setRecyAdapter()
    {
        recyCalculations.layoutManager = LinearLayoutManager(this)
        val adapter = RecyCalAdapter()
        adapter.setData(dataList)
        val dividerItemDecoration = DividerItemDecoration(recyCalculations.context,LinearLayoutManager.VERTICAL)
        recyCalculations.addItemDecoration(dividerItemDecoration)
        recyCalculations.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
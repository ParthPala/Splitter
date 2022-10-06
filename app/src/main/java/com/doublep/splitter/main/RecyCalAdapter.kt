package com.doublep.splitter.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doublep.splitter.R
import com.doublep.splitter.data.CalculationDetailsData

class RecyCalAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val dataList = ArrayList<CalculationDetailsData>()
    lateinit var context: Context

    inner class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        val tvName = viewItem.findViewById<TextView>(R.id.tv_recy_name)
        val tvSplitDetails = viewItem.findViewById<TextView>(R.id.tv_recy_splitDetails)
        val tvTotal = viewItem.findViewById<TextView>(R.id.tv_recy_splitTotal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyrow_calculation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val view = holder as ViewHolder

        view.tvName.text = String.format(
            context.getString(
                R.string.txt_nameDash,
                dataList[position].name.toString()
            )
        )

        dataList[position].reasons.forEach {
            view.tvSplitDetails.append(
                String.format(
                    context.getString(
                        R.string.txt_splitDetails,
                        it.perAmtReason,
                        it.perReasonAmt
                    )
                )
            )
        }
        view.tvTotal.text = String.format(
            context.getString(
                R.string.txt_totalEquals,
                dataList[position].totalAmount
            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: ArrayList<CalculationDetailsData>) {
        this.dataList.clear()
        this.dataList.addAll(data)
    }
}
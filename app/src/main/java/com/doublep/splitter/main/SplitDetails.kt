package com.doublep.splitter.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.doublep.splitter.R

import com.doublep.splitter.customViews.TextViewQuestion
import com.doublep.splitter.customViews.TextViewRestOfAll
import com.doublep.splitter.data.SplitDetailsData
import com.google.gson.Gson
import kotlin.collections.ArrayList


class SplitDetails : AppCompatActivity(), View.OnClickListener {

    private val nameList = ArrayList<String>()
    private lateinit var etReason: AppCompatEditText
    private lateinit var etAmount: AppCompatEditText
    private lateinit var tvSelectPeople: TextView
    private lateinit var tvAddNewBtn: TextView
    private lateinit var tvCalculateBtn: TextView
    private lateinit var tvSelectedPeopleList: TextView
    private val namesSelectedList = ArrayList<String>()
    private val dataList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.split_details)
        val intent = intent
        val bundle = intent.extras
        val data = bundle?.getStringArrayList("data")
        nameList.addAll(data ?: arrayListOf())
        initViews()
        setClicks()
    }

    private fun initViews() {
        etReason = findViewById(R.id.et_type)
        etAmount = findViewById(R.id.et_price)
        tvSelectPeople = findViewById(R.id.tv_selectPeople)
        tvAddNewBtn = findViewById(R.id.tv_addNewBtn)
        tvCalculateBtn = findViewById(R.id.tv_calculateBtn)
        tvSelectedPeopleList = findViewById(R.id.tv_selectedPeopleList)

        tvSelectedPeopleList.visibility = View.GONE

    }

    private fun setClicks() {

        tvSelectPeople.setOnClickListener(this)
        tvAddNewBtn.setOnClickListener(this)
        tvCalculateBtn.setOnClickListener(this)
    }

    private fun openPeopleDialog() {
        namesSelectedList.clear()
        tvSelectedPeopleList.text = ""

        val builder = AlertDialog.Builder(this)
        builder.apply {

            setTitle(getString(R.string.txt_selectPeopleTitle))
            setCancelable(false)
            setMultiChoiceItems(
                nameList.toTypedArray(),
                null,

                DialogInterface.OnMultiChoiceClickListener { _, i, b ->
                    if (b) {
                        namesSelectedList.add(nameList[i])
                    } else if (namesSelectedList.contains(nameList[i])) {
                        namesSelectedList.remove(nameList[i])
                    }
                })
            setPositiveButton(
                "OK",
                DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.cancel()
                    if (namesSelectedList.size > 0) {
                        tvSelectedPeopleList.text = getString(R.string.txt_selectedPeople)
                        tvSelectedPeopleList.visibility = View.VISIBLE

                        namesSelectedList.forEachIndexed { index, i ->
                            tvSelectedPeopleList.append("\n ${index + 1}) $i")
                        }
                    } else
                        tvSelectedPeopleList.visibility = View.GONE
                })
            create()
            show()
        }
    }

    private fun clearFields() {
        etAmount.setText("")
        etReason.setText("")
        etReason.requestFocus()
        tvSelectedPeopleList.text = resources.getString(R.string.txt_selectedPeople)
        namesSelectedList.clear()
        tvSelectedPeopleList.visibility = View.GONE
    }

    private fun saveData() {
        if (etReason.text?.isEmpty() == false || etAmount.text?.isEmpty() == false)
        {
        val reason = etReason.text.toString()
        val amount = etAmount.text.toString().toInt()
        val splitDataObj = SplitDetailsData(reason, amount, namesSelectedList)
        val gson = Gson().toJson(splitDataObj)
        dataList.add(gson.toString())
        }else
        {
            Toast.makeText(this,"Please add data to save",Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendDataToNextPage() {

        if (tvSelectedPeopleList.visibility == View.GONE)
        {
        if (dataList.size > 1) {
            val intent = Intent(this, Calculate::class.java)
            intent.putStringArrayListExtra("dataList", dataList)
            intent.putStringArrayListExtra("nameList", nameList)
            startActivity(intent)
            finish()
        } else
            Toast.makeText(this, "Please enter at least 2 details to calculate.", Toast.LENGTH_LONG)
                .show()
        }
        else{
            Toast.makeText(this, "Please add the current split.", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_selectPeople -> {
                openPeopleDialog()
            }
            R.id.tv_addNewBtn -> {
                saveData()
                clearFields()
            }

            R.id.tv_calculateBtn -> {
                sendDataToNextPage()
            }
        }

    }

}
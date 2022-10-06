package com.doublep.splitter.data

data class CalculationDetailsData(var name : String, var reasons : ArrayList<AmountPerReason>,var totalAmount : Int)
{
     class AmountPerReason(var perAmtReason : String,var perReasonAmt : Int)
}

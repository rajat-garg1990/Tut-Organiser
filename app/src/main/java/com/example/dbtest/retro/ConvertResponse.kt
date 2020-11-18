package com.example.dbtest.retro

data class ConvertResponse(val conversion_rates: Convert)

//data class Currency(val result: Convert)

data class Convert(
var INR:Float,
var USD:Float,
var EUR:Float,
var GBP:Float,
var AUD:Float,
var CAD:Float,
)

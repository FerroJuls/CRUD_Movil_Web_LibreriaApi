package com.example.movillibreria.models


data class prestamo(
    val libro:String,
    val fechaPrestamo:String,
    val fechaDevolucion:String,
    val usuario:String
)

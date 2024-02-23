package com.datospersonales

data class DatoPersonal(
    val id_persona: Int?,
    val nombre: String,
    val apellidos: String,
    val direccion: String,
    val cp: String,
    val ciudad: String,
    val provincia: String,
    val telefono: String
)
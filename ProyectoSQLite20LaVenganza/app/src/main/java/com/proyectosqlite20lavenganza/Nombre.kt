package com.proyectosqlite20lavenganza

class Nombre {
    var id: Int = 0
    var nombreUsuario: String? = null
    var contraseña: String? = null

    constructor(id: Int, nombre: String, contraseña: String) {
        this.id = id
        this.nombreUsuario = nombre
        this.contraseña = contraseña
    }

    constructor(nombre: String, contraseña: String) {
        this.nombreUsuario = nombre
        this.contraseña = contraseña
    }
}

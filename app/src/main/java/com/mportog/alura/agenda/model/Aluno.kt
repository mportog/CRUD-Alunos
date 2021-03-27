package com.mportog.alura.agenda.model

import java.io.Serializable

class Aluno : Serializable {
    var id: Int = 0
    lateinit var nome: String
    lateinit var telefone: String
    lateinit var email: String
    lateinit var ra: String

    constructor(nome: String, telefone: String, email: String, ra: String) {
        this.nome = nome
        this.ra = ra
        this.telefone = telefone
        this.email = email
    }

    constructor()

    override fun toString(): String {
        return nome
    }

    fun idValido(): Boolean {
        return id > 0
    }

}
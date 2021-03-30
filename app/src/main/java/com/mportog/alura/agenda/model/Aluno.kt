package com.mportog.alura.agenda.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Aluno : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    lateinit var nome: String
    lateinit var sobrenome: String
    lateinit var telefone: String
    lateinit var email: String
    lateinit var ra: String

    @Ignore
    constructor(nome: String, sobrenome: String, ra: String, telefone: String, email: String) {
        this.nome = nome
        this.sobrenome = sobrenome
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

    fun nomeCompleto(): String {
        return nome + " " + sobrenome
    }


}
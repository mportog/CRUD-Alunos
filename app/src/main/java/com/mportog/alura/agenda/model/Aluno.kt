package com.mportog.alura.agenda.model

class Aluno {
    var nome: String
    var telefone: String
    var email: String
    var ra: String

    constructor(nome: String, telefone: String, email: String, ra: String) {
        this.nome = nome
        this.ra = ra
        this.telefone = telefone
        this.email = email
    }


}
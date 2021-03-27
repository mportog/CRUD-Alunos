package com.mportog.alura.agenda.dao

import com.mportog.alura.agenda.model.Aluno

class AlunoDAO {

    companion object {

        private var alunos: MutableList<Aluno> = arrayListOf()

        fun salvar(aluno: Aluno) {
            alunos.add(aluno)
        }

        fun todos(): MutableList<String> {
            var list: ArrayList<String> = arrayListOf()
            for (a in alunos) {
                list.add(a.nome)
            }
            return list
        }
    }

}
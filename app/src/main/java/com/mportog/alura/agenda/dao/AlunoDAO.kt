package com.mportog.alura.agenda.dao

import com.mportog.alura.agenda.model.Aluno

class AlunoDAO {

    companion object {

        private var alunos: MutableList<Aluno> = arrayListOf()
        private var idContador: Int = 1

        fun salvar(aluno: Aluno) {
            aluno.id = idContador
            alunos.add(aluno)
            atualizazId()
        }

        private fun atualizazId() {
            idContador++
        }

        fun todos(): MutableList<Aluno> {
            var list: ArrayList<Aluno> = arrayListOf()
            for (a in alunos) {
                list.add(a)
            }
            return list
        }

        fun editar(aluno: Aluno) {
            var alunoencontrado: Aluno? = buscarAlunoPorId(aluno)
            if (alunoencontrado != null) {
                var posicaoAluno: Int = alunos.indexOf(alunoencontrado)
                alunos.set(posicaoAluno, aluno)
            }
        }

        private fun buscarAlunoPorId(aluno: Aluno): Aluno? {
            for (a in alunos) {
                if (a.id == aluno.id) {
                    return a
                }
            }
            return null
        }

        fun remover(aluno:Aluno){
            var alunoAremover:Aluno? = buscarAlunoPorId(aluno)
            if(alunoAremover !=null){
                alunos.remove(aluno)
            }
        }
    }

}
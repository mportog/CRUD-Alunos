package com.mportog.alura.agenda.ui.activity.lista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mportog.alura.agenda.R
import com.mportog.alura.agenda.model.Aluno

class ListaAlunosAdapter(context: Context) : BaseAdapter() {
    private var alunos: MutableList<Aluno>
    private var _context: Context

    init {
        _context = context
        alunos = arrayListOf()
    }

    override fun getCount(): Int {
        return alunos.size
    }

    override fun getItem(position: Int): Aluno {
        return alunos[position]
    }

    override fun getItemId(position: Int): Long {
        return alunos[position].id.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada: View = criarview(parent)
        val alunoDevolvido: Aluno = alunos[position]
        vincularDados(viewCriada, alunoDevolvido)
        return viewCriada
    }

    private fun criarview(parent: ViewGroup?) =
        LayoutInflater.from(_context).inflate(R.layout.item_lista_aluno, parent, false)

    private fun vincularDados(
        viewCriada: View,
        alunoDevolvido: Aluno
    ) {
        val txtNome: TextView = viewCriada.findViewById(R.id.item_lista_nome)
        val txtRa: TextView = viewCriada.findViewById(R.id.item_lista_ra)
        txtNome.text = alunoDevolvido.nomeCompleto()
        txtRa.text = alunoDevolvido.ra
    }

    private fun clear() {
        alunos.clear()
    }

    fun remove(aluno: Aluno) {
        alunos.remove(aluno)
        notifyDataSetChanged()
    }

    private fun addAll(listAlunos: MutableList<Aluno>) {
        alunos.addAll(listAlunos)
    }

    fun atualizar(alunos: MutableList<Aluno>) {
        clear()
        addAll(alunos)
        notifyDataSetChanged()

    }
}
package com.mportog.alura.agenda.ui.activity.lista

import android.app.AlertDialog
import android.content.Context
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import com.mportog.alura.agenda.R
import com.mportog.alura.agenda.dao.AlunoDAO
import com.mportog.alura.agenda.model.Aluno

class ListaAlunosView(val context: Context) {

    private lateinit var adapter: ListaAlunosAdapter

    fun dialogRemocao(item: MenuItem) {
        AlertDialog.Builder(context)
            .setTitle(R.string.remove_dialog_label_title)
            .setMessage(R.string.remove_dialog_label_message)
            .setPositiveButton(
                R.string.remove_buttom_label_yes,
                { dialog, i ->
                    val menuInfo: AdapterView.AdapterContextMenuInfo =
                        item.menuInfo as AdapterView.AdapterContextMenuInfo
                    val alunoescolhido: Aluno? = adapter.getItem(menuInfo.position)
                    if (alunoescolhido != null) {
                        remover(alunoescolhido)
                    }
                })
            .setNegativeButton(R.string.remove_buttom_label_no, null)
            .show()
    }

    fun atualizar() {
        adapter.atualizar(AlunoDAO.todos())
    }

    fun remover(alunoEscolhido: Aluno) {
        AlunoDAO.remover(alunoEscolhido)
        adapter.remove(alunoEscolhido)
    }

    fun configAdapter(lValunos: ListView) {
        adapter =
            ListaAlunosAdapter(context)
        lValunos.adapter = adapter
    }


}
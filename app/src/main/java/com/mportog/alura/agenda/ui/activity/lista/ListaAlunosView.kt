package com.mportog.alura.agenda.ui.activity.lista

import android.app.AlertDialog
import android.content.Context
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import androidx.room.Room
import com.mportog.alura.agenda.R
import com.mportog.alura.agenda.database.AgendaDatabase
import com.mportog.alura.agenda.database.dao.RoomAlunoDAO
import com.mportog.alura.agenda.model.Aluno

class ListaAlunosView {
    private var _context: Context
    private var adapter: ListaAlunosAdapter
    private var dao: RoomAlunoDAO

    constructor(context: Context) {
        _context = context
        adapter =
            ListaAlunosAdapter(_context)
        dao = AgendaDatabase.singleton.instance(_context).getAlunoDAO()
    }

    fun dialogRemocao(item: MenuItem) {
        AlertDialog.Builder(_context)
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
        adapter.atualizar(dao.todos())
    }

    fun remover(alunoEscolhido: Aluno) {
        dao.remover(alunoEscolhido)
        adapter.remove(alunoEscolhido)
    }

    fun configAdapter(lValunos: ListView) {

        lValunos.adapter = adapter
    }


}
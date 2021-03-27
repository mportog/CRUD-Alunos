package com.mportog.alura.agenda.ui.activity

import android.content.Intent
import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mportog.alura.agenda.R
import com.mportog.alura.agenda.dao.AlunoDAO
import com.mportog.alura.agenda.model.Aluno

class ListaAlunosActivity : AppCompatActivity(), ConstantesActivities {
    private lateinit var alunos: MutableList<Aluno>
    private lateinit var adapter: ArrayAdapter<Aluno>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        setTitle(R.string.app_list_page_tittle)
        initFab()
        configLista()
        mockAlunosData()
    }

    private fun mockAlunosData() {
            AlunoDAO.salvar(Aluno("Porto", "11051116", "11986953170", "mportog@mportog.com"))
            AlunoDAO.salvar(Aluno("Alex", "11039816", "117777777", "alex@alura.com"))
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_lista_alunos_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activity_lista_alunos_menu_remover) {
            var menuInfo: AdapterView.AdapterContextMenuInfo =
                item.menuInfo as AdapterView.AdapterContextMenuInfo
            var alunoescolhido: Aluno? = adapter.getItem(menuInfo.position)
            if (alunoescolhido != null) {
                remover(alunoescolhido)
            }
        }

        return super.onContextItemSelected(item)

    }

    override fun onResume() {
        super.onResume()
        atualizarAlunos()
    }

    private fun atualizarAlunos() {
        adapter.clear()
        adapter.addAll(AlunoDAO.todos())
    }

    private fun configLista() {
        var lValunos: ListView = findViewById(R.id.activity_lista_alunos_list)
        alunos = AlunoDAO.todos()
        configAdapter(lValunos)
        configItemListener(lValunos)
        registerForContextMenu(lValunos)
    }

    private fun remover(alunoEscolhido: Aluno) {
        AlunoDAO.remover(alunoEscolhido)
        adapter.remove(alunoEscolhido)
    }

    private fun configItemListener(lValunos: ListView) {
        lValunos.setOnItemClickListener { parent, view, position, id ->
            var alunoEscolhido: Aluno = parent.getItemAtPosition(position) as Aluno
            formularioEdicao(alunoEscolhido)
        }
    }

    private fun formularioEdicao(alunoEscolhido: Aluno) {
        val intent = Intent(this, FormularioAlunoActivity::class.java)
        intent.putExtra(CHAVE_ALUNO, alunoEscolhido)
        startActivity(intent)
    }

    private fun configAdapter(lValunos: ListView) {
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1
        )
        lValunos.setAdapter(
            adapter
        )
    }

    private fun initFab() {
        val fab: FloatingActionButton = findViewById(R.id.activity_lista_alinos_fab_add)
        fab.setOnClickListener {
            formularioCriacao()
        }
    }

    private fun formularioCriacao() {
        val intent = Intent(this, FormularioAlunoActivity::class.java)
        startActivity(intent)
    }

}
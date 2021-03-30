package com.mportog.alura.agenda.ui.activity.lista

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mportog.alura.agenda.R
import com.mportog.alura.agenda.model.Aluno
import com.mportog.alura.agenda.ui.activity.ConstantesActivities
import com.mportog.alura.agenda.ui.activity.formulario.FormularioAlunoActivity

class ListaAlunosActivity : AppCompatActivity(), ConstantesActivities {
    private lateinit var listaAlunosView: ListaAlunosView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        setTitle(R.string.app_list_page_tittle)
        listaAlunosView = ListaAlunosView(this)
        initFab()
        configLista()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.dialogRemocao(item)
        }
        return super.onContextItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        listaAlunosView.atualizar()
    }

    private fun configLista() {
        val lValunos: ListView = findViewById(R.id.activity_lista_alunos_list)
        listaAlunosView.configAdapter(lValunos)
        configItemListener(lValunos)
        registerForContextMenu(lValunos)
    }

    private fun configItemListener(lValunos: ListView) {
        lValunos.setOnItemClickListener { parent, view, position, id ->
            val alunoEscolhido: Aluno = parent.getItemAtPosition(position) as Aluno
            formularioEdicao(alunoEscolhido)
        }
    }

    private fun formularioEdicao(alunoEscolhido: Aluno) {
        val intent = Intent(this, FormularioAlunoActivity::class.java)
        intent.putExtra(CHAVE_ALUNO, alunoEscolhido)
        startActivity(intent)
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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_lista_alunos_menu, menu)
    }

}
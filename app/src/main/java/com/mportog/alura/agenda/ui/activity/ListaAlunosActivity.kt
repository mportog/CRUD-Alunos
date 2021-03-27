package com.mportog.alura.agenda.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mportog.alura.agenda.R
import com.mportog.alura.agenda.dao.AlunoDAO

class ListaAlunosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        setTitle(R.string.app_list_page_tittle)
        initFab()
    }

    override fun onResume() {
        super.onResume()
        var lValunos: ListView = findViewById<ListView>(R.id.activity_lista_alunos_list)
        lValunos.setAdapter(
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                AlunoDAO.todos()
            )
        )
    }

    private fun initFab() {
        val fab: FloatingActionButton = findViewById(R.id.activity_lista_alinos_fab_add)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
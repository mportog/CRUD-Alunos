package com.mportog.alura.agenda.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.mportog.alura.agenda.R
import com.mportog.alura.agenda.dao.AlunoDAO
import com.mportog.alura.agenda.model.Aluno

class FormularioAlunoActivity : AppCompatActivity(), ConstantesActivities {
    private lateinit var txtNome: EditText
    private lateinit var txtRa: EditText
    private lateinit var txtTelefone: EditText
    private lateinit var txtEmail: EditText
    lateinit var aluno: Aluno


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        initCampos()
        carregarAluno()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_formulario_aluno_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.activity_formulario_aluno_salvar){
            finalizarForm()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun carregarAluno() {
        var dados: Intent = intent
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(R.string.app_form_page_edit_tittle)
            aluno = intent.getSerializableExtra(CHAVE_ALUNO) as Aluno
            preencheCampos()
        } else {
            setTitle(R.string.app_form_page_create_tittle)
            aluno = Aluno()
        }
    }

    private fun preencheCampos() {
        txtNome.setText(aluno.nome)
        txtRa.setText(aluno.ra)
        txtTelefone.setText(aluno.telefone)
        txtEmail.setText(aluno.email)
    }

    private fun initCampos() {
        txtNome = findViewById(R.id.activity_formulario_aluno_nome)
        txtRa = findViewById(R.id.activity_formulario_aluno_ra)
        txtTelefone = findViewById(R.id.activity_formulario_aluno_telefone)
        txtEmail = findViewById(R.id.activity_formulario_aluno_email)
    }

    private fun finalizarForm() {
        preencheAluno()
        if (aluno.idValido()) {
            AlunoDAO.editar(aluno)
        } else {
            AlunoDAO.salvar(aluno)
        }
        finish()
    }

    private fun preencheAluno() {
        var nome: String = txtNome.getText().toString()
        var telefone: String = txtTelefone.getText().toString()
        var ra: String = txtRa.getText().toString()
        var email: String = txtEmail.getText().toString()

        aluno.nome = nome
        aluno.ra = ra
        aluno.telefone = telefone
        aluno.email = email
    }
}
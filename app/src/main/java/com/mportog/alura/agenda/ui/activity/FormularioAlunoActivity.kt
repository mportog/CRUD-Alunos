package com.mportog.alura.agenda.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.mportog.alura.agenda.R
import com.mportog.alura.agenda.dao.AlunoDAO
import com.mportog.alura.agenda.model.Aluno

class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var txtNome: EditText
    private lateinit var txtRa: EditText
    private lateinit var txtTelefone: EditText
    private lateinit var txtemail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        setTitle(R.string.app_form_page_tittle)
        initCampos()
        initBtnSalvar()
    }

    private fun initCampos() {
        txtNome = findViewById(R.id.activity_formulario_aluno_nome)
        txtRa = findViewById(R.id.activity_formulario_aluno_ra)
        txtTelefone = findViewById(R.id.activity_formulario_aluno_telefone)
        txtemail = findViewById(R.id.activity_formulario_aluno_email)
    }

    private fun initBtnSalvar() {
        var btnSalvar: Button = findViewById<Button>(R.id.activity_formulario_aluno_btn_salvar)
        btnSalvar.setOnClickListener {
            var alunocriado: Aluno = criarAluno()
            salvarAluno(alunocriado)
        }
    }

    private fun criarAluno(): Aluno {
        var nome: String = txtNome.getText().toString()
        var telefone: String = txtTelefone.getText().toString()
        var ra: String = txtRa.getText().toString()
        var email: String = txtemail.getText().toString()
        var alunocriado: Aluno = Aluno(nome, ra, email, telefone)
        return alunocriado
    }

    private fun salvarAluno(alunocriado: Aluno) {
        AlunoDAO.salvar(alunocriado)
        finish()
    }


}
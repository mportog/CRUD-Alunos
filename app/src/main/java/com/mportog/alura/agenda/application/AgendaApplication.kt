package com.mportog.alura.agenda.application

import android.app.Application
import com.mportog.alura.agenda.dao.AlunoDAO
import com.mportog.alura.agenda.model.Aluno

class AgendaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
            AlunoDAO.salvar(Aluno("Porto", "11051116", "11986953170", "mportog@mportog.com"))
            AlunoDAO.salvar(Aluno("Alex", "11039816", "117777777", "alex@alura.com"))
        }
    }
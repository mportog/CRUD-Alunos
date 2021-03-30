package com.mportog.alura.agenda.database.dao

import androidx.room.*
import com.mportog.alura.agenda.model.Aluno

@Dao
interface RoomAlunoDAO {

    @Insert
    fun salvar(aluno: Aluno)

    @Query("SELECT * FROM aluno")
    fun todos(): MutableList<Aluno>

    @Delete
    fun remover(aluno: Aluno)

    @Update
    fun editar(aluno: Aluno)

}
package com.mportog.alura.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mportog.alura.agenda.database.converter.ConversorCalendar
import com.mportog.alura.agenda.database.dao.RoomAlunoDAO
import com.mportog.alura.agenda.model.Aluno

@Database(entities = arrayOf(Aluno::class), version = 2, exportSchema = false)

@TypeConverters(ConversorCalendar::class)
abstract class AgendaDatabase() : RoomDatabase() {
    abstract fun getAlunoDAO(): RoomAlunoDAO

    object singleton {
        private const val databaseName: String = "agenda-db"
        fun instance(context: Context): AgendaDatabase {

            return Room.databaseBuilder(
                context,
                AgendaDatabase::class.java, databaseName
            )
                .addMigrations(*AgendaMigrations.TODAS_MIGRATIONS)
                .allowMainThreadQueries()
                .build()
        }
    }
}
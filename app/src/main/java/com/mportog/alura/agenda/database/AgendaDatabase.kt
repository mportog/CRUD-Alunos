package com.mportog.alura.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mportog.alura.agenda.database.dao.RoomAlunoDAO
import com.mportog.alura.agenda.model.Aluno

@Database(entities = arrayOf(Aluno::class), version = 2, exportSchema = false)
abstract class AgendaDatabase() : RoomDatabase() {
    abstract fun getAlunoDAO(): RoomAlunoDAO

    object singleton {
        private const val databaseName: String = "agenda-db"
        fun instance(context: Context): AgendaDatabase {

            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL(
                        "ALTER TABLE aluno ADD COLUMN sobrenome TEXT"
                    )
                }
            }

            return Room.databaseBuilder(
                context,
                AgendaDatabase::class.java, databaseName
            )
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build()
        }
    }
}
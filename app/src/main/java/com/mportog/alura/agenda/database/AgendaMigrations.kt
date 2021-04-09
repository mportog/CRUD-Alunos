package com.mportog.alura.agenda.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


internal object AgendaMigrations {
    fun TODAS_MIGATIONS() = arrayOf(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)

    private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT")
        }
    }
    private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Criar nova tabela com as informações desejadas
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                        "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`nome` TEXT, " +
                        "`telefone` TEXT, " +
                        "`email` TEXT)"
            )

            // Copiar dados da tabela antiga para a nova
            database.execSQL(
                "INSERT INTO Aluno_novo (id, nome, telefone, email) " +
                        "SELECT id, nome, telefone, email FROM Aluno"
            )

            // Remove tabela antiga
            database.execSQL("DROP TABLE Aluno")

            // Renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno")
        }
    }
    private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER")
        }
    }
    val TODAS_MIGRATIONS = arrayOf(
        MIGRATION_1_2,
        MIGRATION_2_3,
        MIGRATION_3_4
    )
}
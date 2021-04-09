package com.mportog.alura.agenda.database.converter

import androidx.room.TypeConverter
import java.util.*

class ConversorCalendar {

    @TypeConverter
    fun paraLong(valor: Calendar): Long? {
        if (valor != null) return valor.timeInMillis
        return null
    }

    @TypeConverter
    fun paraCalendar(valor: Long): Calendar {
        var momentoAtual: Calendar = Calendar.getInstance()
        valor.let {
            momentoAtual.timeInMillis = it
        }
        return momentoAtual
    }
}

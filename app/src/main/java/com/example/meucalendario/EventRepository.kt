package com.example.meucalendario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EventRepository {

    // Dados fictícios
    private val mockData = listOf(
        DataEvents(date = 5, description = "Troque sua escova de dentes por uma nova"),
        DataEvents(date = 15, description = "Marque uma consulta com seu dentista"),
        DataEvents(date = 30, description = "Exame agendado"),
    )

    private val _items = MutableLiveData<List<DataEvents>>(mockData)
    val events: LiveData<List<DataEvents>> get() = _items
}
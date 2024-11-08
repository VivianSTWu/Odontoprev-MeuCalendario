package com.example.meucalendario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EventRepository {

    // Dados fictícios
    private val mockData = listOf(
        DataEvents(id = "32", date = 5, description = "Troque sua escova de dentes por uma nova"),
        DataEvents(id = "54", date = 15, description = "Marque uma consulta com seu dentista"),
        DataEvents(id = "6768", date = 30, description = "Exame agendado"),
    )

    private val _items = MutableLiveData<List<DataEvents>>(mockData)
    val events: LiveData<List<DataEvents>> get() = _items
}
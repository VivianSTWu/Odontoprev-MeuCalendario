package com.example.meucalendario

import androidx.lifecycle.ViewModel

class EventViewModel : ViewModel() {
    private val repository = EventRepository()
    val events = repository.events
}
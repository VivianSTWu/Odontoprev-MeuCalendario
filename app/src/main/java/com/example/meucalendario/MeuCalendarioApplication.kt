package com.example.meucalendario

import android.app.Application
import android.util.Log

class MeuCalendarioApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Configuração do uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("AppError", "Uncaught exception in thread ${thread.name}", throwable)
            // Aqui você pode fazer outras ações, como notificar o servidor ou exibir um diálogo para o usuário
        }
    }
}

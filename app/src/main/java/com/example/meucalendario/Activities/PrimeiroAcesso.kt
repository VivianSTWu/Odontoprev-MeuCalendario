package com.example.meucalendario.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meucalendario.R

class PrimeiroAcesso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Primeiro infla o layout
        setContentView(R.layout.fragment_primeiro_acesso)

        // Depois referencie o botão pelo ID
        val sendButton: Button = findViewById(R.id.btn_questionario)

        // Ative o comportamento Edge-to-Edge
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Defina o clique para ir para Pg_Questionario
        sendButton.setOnClickListener {
            val intent = Intent(this, Pg_Questionario::class.java)
            startActivity(intent)
        }
    }
}

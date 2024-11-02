package com.example.meucalendario.Activities

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.meucalendario.R
import java.util.Calendar

class Pg_Questionario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pg_questionario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextDate = findViewById<EditText>(R.id.editTextDate)
        val msgErro = findViewById<TextView>(R.id.msg_erro)
        val btnEnviar = findViewById<Button>(R.id.btn_enviar)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        val textViewIds = listOf(
            R.id.pergunta1,
            R.id.pergunta4
        )

        formatTextViewsWithAsterisks(textViewIds)

        editTextDate.setOnClickListener {
            showDatePickerDialog(editTextDate)
    }

        btnEnviar.setOnClickListener {
            val dataSelecionada = editTextDate.text.toString()
            val radioSelecionadoId = radioGroup.checkedRadioButtonId

            if (dataSelecionada.isEmpty() || radioSelecionadoId == -1) {
                msgErro.isVisible = true
            } else {
                msgErro.isVisible = false
                // Enviar para a próxima página
            }
        }
}

    private fun formatTextViewsWithAsterisks(ids: List<Int>) {
        for (id in ids) {
            val textView = findViewById<TextView>(id)
            val text = textView.text.toString()
            if (text.contains("*")) {
                val spannable = SpannableString(text)
                var index = text.indexOf("*")
                while (index >= 0) {
                    spannable.setSpan(
                        ForegroundColorSpan(Color.RED),
                        index,
                        index + 1,
                        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    index = text.indexOf("*", index + 1)
                }
                textView.text = spannable
            }
        }
    }


    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            R.style.CustomDatePickerTheme,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                editText.setText(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }

}
package com.example.meucalendario

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.meucalendario.databinding.FragmentAddEventoBinding
import com.example.meucalendario.databinding.FragmentQuestionarioBinding
import java.util.Calendar

class AddEventoFragment: Fragment() {
    private var _binding: FragmentAddEventoBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEventoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.calendarioFragment)
        }

        binding.editTextDate.setOnClickListener {
            showDatePickerDialog(binding.editTextDate)
        }

        binding.btnEnviar.setOnClickListener {
            val dataSelecionada = binding.editTextDate.text.toString()
            val radioSelecionadoId = binding.radioGroup.checkedRadioButtonId

            if (dataSelecionada.isEmpty() || radioSelecionadoId == -1) {
                binding.msgErro.isVisible = true
            } else {
                binding.msgErro.isVisible = false
                findNavController().navigate(R.id.calendarioFragment)
            }
        }
    }


    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),  // Use requireContext() no Fragment
            R.style.CustomDatePickerTheme,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                editText.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.meucalendario

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meucalendario.databinding.FragmentAddEventoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class AddEventoFragment : Fragment() {
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
            val tipoEvento = binding.radioGroup.findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId)?.text?.toString() ?: ""
            val descricaoEvento = tipoEvento  // descrição do evento é o mesmo texto do tipo

            if (dataSelecionada.isEmpty() || tipoEvento.isEmpty()) {
                binding.msgErro.isVisible = true
            } else {
                binding.msgErro.isVisible = false
                criarEvento(tipoEvento, descricaoEvento, dataSelecionada)
            }
        }
    }


    private fun criarEvento(tipoEvento: String, descricaoEvento: String, data: String) {
        val idCliente = "c67e6898-2153-4bd4-9a2c-d2137dd49b99"  // Insira o ID do cliente
        val novoEvento = NetworkEventoCreate(
            tipo_evento = tipoEvento,
            desc_evento = descricaoEvento,
            dt_evento = data,
            fk_cliente = NetworkEventoCreate.ClienteId(id_cliente = idCliente)
        )

        val service = Api.buildService()
        val request = service.createEvent(novoEvento)

        request.enqueue(object : Callback<NetworkEventoCreate> {
            override fun onResponse(call: Call<NetworkEventoCreate>, response: Response<NetworkEventoCreate>) {
                if (response.isSuccessful) {
                    // Navega de volta ao CalendarioFragment em caso de sucesso
                    findNavController().navigate(R.id.calendarioFragment)
                } else {
                    // Logar a resposta em caso de erro
                    binding.msgErro.text = "Erro ao criar evento. Código: ${response.code()}"
                    binding.msgErro.isVisible = true
                }
            }

            override fun onFailure(call: Call<NetworkEventoCreate>, t: Throwable) {
                // Logar o erro no caso de falha de rede ou outro erro
                binding.msgErro.text = "Falha na comunicação com o servidor: ${t.message}"
                binding.msgErro.isVisible = true
            }
        })
    }





    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.CustomDatePickerTheme,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Formatação da data para o formato "YYYY-MM-DD"
                val selectedDate = "${selectedYear}-${selectedMonth + 1}-${selectedDay}"
                // Exibe a data no EditText
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

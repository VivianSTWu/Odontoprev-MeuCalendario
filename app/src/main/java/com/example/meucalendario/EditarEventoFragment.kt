package com.example.meucalendario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meucalendario.databinding.FragmentEditarEventoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response  // Certifique-se de que o import correto seja este

class EditarEventoFragment : Fragment() {

    private var _binding: FragmentEditarEventoBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventId: String // Variável para armazenar o ID do evento

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarEventoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.calendarioFragment)
        }

        // Pega o ID do evento que foi passado
        eventId = arguments?.getString("eventId") ?: ""

        binding.btnExcluir.setOnClickListener {
            if (eventId.isNotEmpty()) {
                deletarEvento(eventId) // Passa o ID para deletar o evento
            } else {
                // Caso não tenha o ID, exibe uma mensagem de erro
                Toast.makeText(requireContext(), "ID do evento não encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deletarEvento(eventId: String) {
        val service = Api.buildService()
        val request = service.deleteEvent(eventId)

        request.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Evento deletado com sucesso, volte para o calendário
                    Toast.makeText(requireContext(), "Evento deletado com sucesso", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.calendarioFragment) // Navega de volta para o calendário
                } else {
                    // Caso a resposta não seja bem-sucedida
                    Toast.makeText(requireContext(), "Erro ao excluir o evento", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Caso ocorra uma falha na comunicação
                Toast.makeText(requireContext(), "Falha ao conectar com o servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.meucalendario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meucalendario.databinding.FragmentCalendarioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class CalendarioFragment : Fragment(R.layout.fragment_calendario), EventsAdapter.OnEventClickListener {

    private var _binding: FragmentCalendarioBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventsAdapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Função para filtrar e ordenar eventos
    private fun filterAndSortEvents(events: List<NetworkEvento>): List<DataEvents> {
        val today = LocalDate.now()
        return events
            .filter { LocalDate.parse(it.dt_evento) >= today } // Filtra os eventos que não passaram
            .sortedBy { it.dt_evento } // Ordena os eventos em ordem cronológica
            .map { mapNetworkEventoToDataEvents(it) } // Mapeia para DataEvents
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar o adapter vazio e configurar o RecyclerView
        eventsAdapter = EventsAdapter(emptyList(), this)
        binding.recyclerView.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Fazer a chamada para a API
        val service = Api.buildService()
        val request = service.getAllUsersEvents("c67e6898-2153-4bd4-9a2c-d2137dd49b99")

        request.enqueue(object : Callback<List<NetworkEvento>> {
            override fun onResponse(call: Call<List<NetworkEvento>>, response: Response<List<NetworkEvento>>) {
                if (response.isSuccessful) {
                    val networkEventos = response.body() ?: emptyList()
                    val events = filterAndSortEvents(networkEventos)
                    if (events.isNotEmpty()) {
                        // Obtém o mês do primeiro evento
                        val firstEventDate = LocalDate.parse(networkEventos[0].dt_evento)
                        val monthName = firstEventDate.format(DateTimeFormatter.ofPattern("MMMM"))
                        binding.textMonth.text = monthName.capitalize() // Exibe o nome do mês
                    }
                    eventsAdapter.setEvents(events) // Atualiza a lista de eventos no adapter
                }
            }

            override fun onFailure(call: Call<List<NetworkEvento>>, error: Throwable) {
                // Tratar o erro aqui
            }
        })


        // Configurar o botão para adicionar novos eventos
        binding.btnAddEvent.setOnClickListener {
            findNavController().navigate(R.id.addEventoFragment)
        }
    }


    override fun onEventClick(event: DataEvents) {
        val action = CalendarioFragmentDirections.actionCalendarioFragmentToEditarEventoFragment(event.id)
        findNavController().navigate(action)
    }


    private fun mapNetworkEventoToDataEvents(networkEvento: NetworkEvento): DataEvents {
        val day = networkEvento.dt_evento.substring(8, 10).toInt()  // Extrair o dia
        val description = networkEvento.desc_evento
        val id = networkEvento.id_evento

        return DataEvents(id, day, description)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.meucalendario

import android.os.Bundle
import android.provider.CalendarContract.Events
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meucalendario.databinding.FragmentCalendarioBinding
import com.example.meucalendario.databinding.FragmentPrimeiroAcessoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalendarioFragment: Fragment() {
    private var _binding: FragmentCalendarioBinding? = null

    private val binding get() = _binding!!

    private val viewModel : EventViewModel by viewModels()
    private lateinit var eventsAdapter: EventsAdapter

/*    private lateinit var adapter: EventsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventsArrayList: ArrayList<DataEvents>

    lateinit var dateId: Array<Int>
    lateinit var descriptions: Array<String>*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsAdapter = EventsAdapter(emptyList())
        val recyclerView = binding.recyclerView
        recyclerView.adapter = eventsAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observar os dados mockados
        viewModel.events.observe(viewLifecycleOwner) { items ->
            eventsAdapter = EventsAdapter(items)
            recyclerView.adapter = eventsAdapter
        }

/*        super.onViewCreated(view, savedInstanceState)
        dataInitialize()

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = EventsAdapter(eventsArrayList)
        recyclerView.adapter = adapter*/

        /*val service = Api.buildService()

        val request = service.getAllUsersEvents()

        request.enqueue(object : Callback<List<NetworkEvento>> {
            override fun onResponse(
                call: Call<List<NetworkEvento>>,
                response: Response<List<NetworkEvento>>
            ) {
                val list: List<NetworkEvento>? = response.body()

                binding.eventDescription.text = list?.size.toString()
            }

            override fun onFailure(call: Call<List<NetworkEvento>>, error: Throwable) {
                TODO("Not yet implemented")
            }

        })*/

        binding.btnAddEvent.setOnClickListener {
            findNavController().navigate(R.id.addEventoFragment)
        }

        binding.eventGroup.setOnClickListener {
            findNavController().navigate(R.id.editarEventoFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

/*
    private fun dataInitialize(){

        eventsArrayList = arrayListOf<DataEvents >()

        dateId = arrayOf(
            3,5,13,30
        )

        descriptions = arrayOf(
            "Marque uma consulta com seu dentista",
            "Troque sua escova de dente por uma nova",
            "Exame agendado",
            "Exame agendado"
        )

        for (i in dateId.indices){
            val event = DataEvents(dateId[i], descriptions[i])
            eventsArrayList.add(event)
        }
    }*/
}


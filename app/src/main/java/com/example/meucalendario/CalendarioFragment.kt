package com.example.meucalendario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meucalendario.databinding.FragmentCalendarioBinding
import com.example.meucalendario.databinding.FragmentPrimeiroAcessoBinding

class CalendarioFragment: Fragment() {
    private var _binding: FragmentCalendarioBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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
}
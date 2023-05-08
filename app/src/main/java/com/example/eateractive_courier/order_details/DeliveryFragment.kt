package com.example.eateractive_courier.order_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.eateractive_courier.R
import com.example.eateractive_courier.cart.OrderViewModel
import com.example.eateractive_courier.cart.OrderViewModelFactory
import com.example.eateractive_courier.cart.cartDatabase
import androidx.navigation.fragment.findNavController
import com.example.eateractive_courier.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment() {
    private var _binding: FragmentDeliveryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OrderViewModel by viewModels {
        OrderViewModelFactory(cartDatabase(requireActivity().applicationContext))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = getString(R.string.delivery_address)
        binding.confirmButton.setOnClickListener {
            val navController = findNavController()
            navController.popBackStack()
            navController.popBackStack()
        }
    }
}
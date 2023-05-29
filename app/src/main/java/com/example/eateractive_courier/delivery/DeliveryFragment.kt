package com.example.eateractive_courier.delivery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.eateractive_courier.R
import com.example.eateractive_courier.databinding.FragmentDeliveryBinding
import com.example.eateractive_courier.server.ServerApi
import com.example.eateractive_courier.server.ServerViewModel

class DeliveryFragment : Fragment() {
    private var _binding: FragmentDeliveryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DeliveryViewModel by viewModels {
        DeliveryViewModelFactory(
            ServerViewModel.getInstance(
                activity?.getPreferences(Context.MODE_PRIVATE)
                    ?.getString(getString(R.string.jwt_key), "qwerty")
            ).create(ServerApi::class.java),
            requireArguments().getInt(KEY_ARG_ORDER_ID)
        )
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

        viewModel.deliveryAddress.observe(viewLifecycleOwner) { address ->
            binding.address.text = address
        }

        binding.confirmButton.setOnClickListener {
            viewModel.confirmDelivered()

            val navController = findNavController()
            navController.popBackStack()
            navController.popBackStack()
        }
    }

    companion object {
        const val KEY_ARG_ORDER_ID = "orderId"
    }
}
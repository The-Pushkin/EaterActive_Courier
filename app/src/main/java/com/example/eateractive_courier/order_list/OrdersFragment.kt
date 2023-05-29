package com.example.eateractive_courier.order_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eateractive_courier.R
import com.example.eateractive_courier.databinding.FragmentOrdersBinding
import com.example.eateractive_courier.server.ServerApi
import com.example.eateractive_courier.server.ServerViewModel

class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OrdersViewModel by viewModels {
        OrdersViewModelFactory(
            ServerViewModel.getInstance(
                activity?.getPreferences(Context.MODE_PRIVATE)
                    ?.getString(getString(R.string.jwt_key), "qwerty")
            ).create(ServerApi::class.java)
        )
    }

    private lateinit var adapter: OrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        binding.logoutButton.setOnClickListener {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            if (sharedPref != null) {
                with(sharedPref.edit()) {
                    clear()
                    apply()
                }
            }

            findNavController().popBackStack(R.id.mainFragment, false)
        }

        adapter = OrdersAdapter { bundle ->
            findNavController().navigate(
                R.id.action_ordersFragment_to_orderDetailsFragment,
                bundle
            )
        }
        adapter.submitList(emptyList())
        binding.ordersList.adapter = adapter
        binding.ordersList.layoutManager = LinearLayoutManager(context)

        viewModel.orders.observe(viewLifecycleOwner) { orders ->
            val items = orders.map { OrdersModel.Order(it.id, it.name, it.itemCount) }
            adapter.submitList(items)
        }

        return binding.root
    }
}
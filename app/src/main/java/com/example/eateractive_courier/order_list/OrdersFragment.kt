package com.example.eateractive_courier.order_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eateractive_courier.R
import com.example.eateractive_courier.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        val items: MutableList<OrdersModel> = mutableListOf(
            OrdersModel.Divider,
            OrdersModel.Orders("Cocosu' Rosu", 3),
            OrdersModel.Divider,
            OrdersModel.Orders("Taco Bell", 2),
            OrdersModel.Divider,
            OrdersModel.Orders("Burger King", 5),
            OrdersModel.Divider,
            OrdersModel.Orders("Una Mica La Georgica", 8),
            OrdersModel.Divider,
            OrdersModel.Orders("Starbucks", 2),
            OrdersModel.Divider,
            OrdersModel.Orders("McDonald's", 12),
            OrdersModel.Divider,
            OrdersModel.Orders("KFC", 9),
            OrdersModel.Divider,
            OrdersModel.Orders("Mesopotamia", 1),
            OrdersModel.Divider,
            OrdersModel.Orders("Shaormeria Baneasa", 1),
            OrdersModel.Divider,
            OrdersModel.Orders("BO$$ BURGERS", 3),
            OrdersModel.Divider,
            OrdersModel.Orders("Popeye's Chicken", 5),
            OrdersModel.Divider,
            OrdersModel.Orders("Dunkin' Donuts", 2),
            OrdersModel.Divider,
            OrdersModel.Orders("Hell Kitchen", 21),
            OrdersModel.Divider,
            OrdersModel.Orders("Garden Pub", 1),
            OrdersModel.Divider,
            OrdersModel.Orders("The Place", 1),
            OrdersModel.Divider,
        )

        val adapter = OrdersAdapter { bundle ->
            findNavController().navigate(
                R.id.action_ordersFragment_to_orderDetailsFragment,
                bundle
            )
        }
        adapter.submitList(items)
        binding.ordersList.adapter = adapter
        binding.ordersList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}
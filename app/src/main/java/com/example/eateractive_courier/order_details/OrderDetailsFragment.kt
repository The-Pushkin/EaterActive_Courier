package com.example.eateractive_courier.order_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eateractive_courier.R
import com.example.eateractive_courier.cart.OrderViewModel
import com.example.eateractive_courier.cart.OrderViewModelFactory
import com.example.eateractive_courier.cart.cartDatabase
import com.example.eateractive_courier.databinding.FragmentOrderDetailsBinding

class OrderDetailsFragment : Fragment() {
    private var _binding: FragmentOrderDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OrderViewModel by viewModels {
        OrderViewModelFactory(cartDatabase(requireActivity().applicationContext))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)

        val items: MutableList<OrderItemModel> = mutableListOf(
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Pastrama de oaie pe ceapa"),
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Caramel Latte"),
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Piept de pui umplut cu cascaval, verdeata si usturoi"),
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Frigarui de creveti gatiti in unt"),
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Sarmale cu mamaliguta"),
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Alegatura de porc la garnita cu ou"),
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Choice Grade Ribeye Steak"),
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Pranzul Haiducului"),
            OrderItemModel.Divider,
            OrderItemModel.OrderItem("Mici Cu Bere"),
            OrderItemModel.Divider,
        )

        binding.title.text = requireArguments().getString(KEY_ARG_RESTAURANT_NAME)

        val adapter = OrderItemListAdapter { }
        adapter.submitList(items)
        binding.menuItemList.adapter = adapter
        binding.menuItemList.layoutManager = LinearLayoutManager(context)

        binding.checkoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_orderDetailsFragment_to_deliveryFragment)
        }

        return binding.root
    }

    companion object {
        const val KEY_ARG_RESTAURANT_NAME = "restaurantName"
    }
}
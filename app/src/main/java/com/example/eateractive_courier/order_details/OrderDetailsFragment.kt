package com.example.eateractive_courier.order_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eateractive_courier.R
import com.example.eateractive_courier.databinding.FragmentOrderDetailsBinding
import com.example.eateractive_courier.delivery.DeliveryFragment
import com.example.eateractive_courier.server.ServerApi
import com.example.eateractive_courier.server.ServerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderDetailsFragment : Fragment() {
    private var _binding: FragmentOrderDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var serverApi: ServerApi
    private lateinit var adapter: OrderItemListAdapter

    private val viewModel: OrderDetailsViewModel by viewModels {
        OrderDetailsViewModelFactory(
            ServerViewModel.getInstance().create(ServerApi::class.java),
            requireArguments().getInt(KEY_ARG_ORDER_ID)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)

        val restaurantName = requireArguments().getString(KEY_ARG_RESTAURANT_NAME)
        val orderId = requireArguments().getInt(KEY_ARG_ORDER_ID)

        binding.title.text = restaurantName

        adapter = OrderItemListAdapter { }
        adapter.submitList(emptyList())
        binding.menuItemList.adapter = adapter
        binding.menuItemList.layoutManager = LinearLayoutManager(context)

        serverApi = ServerViewModel.getInstance().create(ServerApi::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            val address = serverApi.getRestaurantAddress(orderId).body()?.address ?: "idk"
            binding.restaurantAddress.text = address

            val items = serverApi.getOrderItems(orderId).body()
                ?.map { OrderItemModel.OrderItem(it.id, it.name) }

            adapter.submitList(items)
        }

        binding.confirmPickupButton.setOnClickListener {
            viewModel.confirmPickup()

            findNavController().navigate(
                R.id.action_orderDetailsFragment_to_deliveryFragment,
                bundleOf(DeliveryFragment.KEY_ARG_ORDER_ID to orderId)
            )
        }

        return binding.root
    }

    companion object {
        const val KEY_ARG_RESTAURANT_NAME = "restaurantName"
        const val KEY_ARG_ORDER_ID = "orderId"
    }
}
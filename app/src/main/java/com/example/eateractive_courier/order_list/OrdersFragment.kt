package com.example.eateractive_courier.order_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eateractive_courier.R
import com.example.eateractive_courier.databinding.FragmentOrdersBinding
import com.example.eateractive_courier.server.ServerApi
import com.example.eateractive_courier.server.ServerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var serverApi: ServerApi
    private lateinit var adapter: OrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        adapter = OrdersAdapter { bundle ->
            findNavController().navigate(
                R.id.action_ordersFragment_to_orderDetailsFragment,
                bundle
            )
        }
        adapter.submitList(emptyList())
        binding.ordersList.adapter = adapter
        binding.ordersList.layoutManager = LinearLayoutManager(context)

        serverApi = ServerViewModel.getInstance().create(ServerApi::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            val items =
                serverApi.getOrders().body()
                    ?.map { OrdersModel.Order(it.id, it.name, it.itemCount) }
            adapter.submitList(items)
        }

        return binding.root
    }
}
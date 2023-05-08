package com.example.eateractive_courier.order_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eateractive_courier.R
import com.example.eateractive_courier.databinding.DividerRowBinding
import com.example.eateractive_courier.databinding.OrderRowBinding
import com.example.eateractive_courier.order_details.OrderDetailsFragment

class OrdersAdapter(private val onClickCallback: (Bundle) -> Unit) :
    ListAdapter<OrdersModel, RestaurantsAdapterViewHolder>(RestaurantModelCallback) {
    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is OrdersModel.Orders -> RestaurantsAdapterViewHolder.RESTAURANT_ITEM
        is OrdersModel.Divider -> RestaurantsAdapterViewHolder.DIVIDER_ITEM
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantsAdapterViewHolder =
        when (viewType) {
            RestaurantsAdapterViewHolder.RESTAURANT_ITEM -> {
                val binding = OrderRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                RestaurantsAdapterViewHolder.RestaurantViewHolder(binding, onClickCallback)
            }
            RestaurantsAdapterViewHolder.DIVIDER_ITEM -> {
                val binding = DividerRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                RestaurantsAdapterViewHolder.DividerViewHolder(binding)
            }
            else -> {
                error("ViewType $viewType is not supported.")
            }
        }

    override fun onBindViewHolder(holder: RestaurantsAdapterViewHolder, position: Int) =
        holder.bind(currentList[position])
}

sealed class RestaurantsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: OrdersModel)
    class RestaurantViewHolder(
        private val binding: OrderRowBinding,
        private val onClickCallback: (Bundle) -> Unit
    ) : RestaurantsAdapterViewHolder(binding.root) {
        override fun bind(item: OrdersModel) {
            val orderItem = item as? OrdersModel.Orders ?: return
            with(binding) {
                restaurantName.text = orderItem.name
                itemCount.text = itemView.context.getString(
                    R.string.item_count_string,
                    orderItem.itemCount
                )
                root.setOnClickListener {
                    onClickCallback(
                        bundleOf(
                            OrderDetailsFragment.KEY_ARG_RESTAURANT_NAME to orderItem.name
                        )
                    )
                }
            }
        }
    }

    class DividerViewHolder(
        binding: DividerRowBinding
    ) : RestaurantsAdapterViewHolder(binding.root) {
        override fun bind(item: OrdersModel) = Unit
    }

    companion object {
        /** ViewType of [OrdersModel.Orders]. */
        const val RESTAURANT_ITEM = 1

        /** ViewType of [OrdersModel.Divider]. */
        const val DIVIDER_ITEM = 2
    }
}

object RestaurantModelCallback : DiffUtil.ItemCallback<OrdersModel>() {
    override fun areItemsTheSame(oldItem: OrdersModel, newItem: OrdersModel): Boolean =
        when (oldItem) {
            is OrdersModel.Orders -> {
                newItem is OrdersModel.Orders &&
                        newItem.name == oldItem.name
            }
            is OrdersModel.Divider -> {
                newItem is OrdersModel.Divider
            }
        }

    override fun areContentsTheSame(oldItem: OrdersModel, newItem: OrdersModel): Boolean =
        oldItem == newItem
}
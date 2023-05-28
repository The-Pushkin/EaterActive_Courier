package com.example.eateractive_courier.order_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eateractive_courier.cart.entity.OrderItemEntity
import com.example.eateractive_courier.databinding.DividerRowBinding
import com.example.eateractive_courier.databinding.OrderItemRowBinding

class OrderItemListAdapter(private val onClickCallback: (OrderItemEntity) -> Unit) :
    ListAdapter<OrderItemModel, MenuItemListAdapterViewHolder>(MenuItemModelCallback) {
    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is OrderItemModel.OrderItem -> MenuItemListAdapterViewHolder.MENU_ITEM
        is OrderItemModel.Divider -> MenuItemListAdapterViewHolder.DIVIDER_ITEM
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuItemListAdapterViewHolder = when (viewType) {
        MenuItemListAdapterViewHolder.MENU_ITEM -> {
            val binding = OrderItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            MenuItemListAdapterViewHolder.MenuItemViewHolder(binding, onClickCallback)
        }
        MenuItemListAdapterViewHolder.DIVIDER_ITEM -> {
            val binding = DividerRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            MenuItemListAdapterViewHolder.DividerViewHolder(binding)
        }
        else -> {
            error("ViewType $viewType is not supported.")
        }
    }

    override fun onBindViewHolder(holder: MenuItemListAdapterViewHolder, position: Int) =
        holder.bind(currentList[position])
}

sealed class MenuItemListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: OrderItemModel)

    class MenuItemViewHolder(
        private val binding: OrderItemRowBinding,
        private val onClickCallback: (OrderItemEntity) -> Unit
    ) : MenuItemListAdapterViewHolder(binding.root) {
        override fun bind(item: OrderItemModel) {
            val menuItem = item as? OrderItemModel.OrderItem ?: return
            with(binding) {
                menuItemName.text = menuItem.orderItemEntity.name
                root.setOnClickListener {
                    onClickCallback(menuItem.orderItemEntity)
                }
            }
        }
    }

    class DividerViewHolder(
        binding: DividerRowBinding
    ) : MenuItemListAdapterViewHolder(binding.root) {
        override fun bind(item: OrderItemModel) = Unit
    }

    companion object {
        /** ViewType of [OrderItemModel.OrderItem]. */
        const val MENU_ITEM = 1

        /** ViewType of [OrderItemModel.Divider]. */
        const val DIVIDER_ITEM = 2
    }
}

object MenuItemModelCallback : DiffUtil.ItemCallback<OrderItemModel>() {
    override fun areItemsTheSame(oldItem: OrderItemModel, newItem: OrderItemModel): Boolean =
        when (oldItem) {
            is OrderItemModel.OrderItem -> {
                newItem is OrderItemModel.OrderItem &&
                        newItem.orderItemEntity.serverId == oldItem.orderItemEntity.serverId &&
                        newItem.orderItemEntity.name == oldItem.orderItemEntity.name
            }
            is OrderItemModel.Divider -> {
                newItem is OrderItemModel.Divider
            }
        }

    override fun areContentsTheSame(oldItem: OrderItemModel, newItem: OrderItemModel): Boolean =
        oldItem == newItem
}
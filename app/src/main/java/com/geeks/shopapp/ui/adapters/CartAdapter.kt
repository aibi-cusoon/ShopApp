package com.geeks.shopapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.geeks.shopapp.databinding.ItemCartBinding
import com.geeks.shopapp.domain.models.CartItem

class CartAdapter :
    ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) {
            with(binding) {
                tvTitle.text = item.product.title
                tvPrice.text = "${item.product.price}$"
                tvQuantity.text = "x${item.quantity}"
                ivProduct.load(item.product.image)
            }
        }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}

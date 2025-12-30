package com.geeks.shopapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.geeks.shopapp.databinding.ItemProductBinding
import com.geeks.shopapp.domain.models.Product

class ProductAdapter(
    private val onClick: (Product) -> Unit,
    private val onBuyClick: (Product) -> Unit

) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffUtilCallback()) {

    class ProductDiffUtilCallback: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val binding = ItemProductBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ProductViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                tvTitle.text = product.title
                tvPrice.text = "${product.price}$"
                tvCategory.text = product.category

                ivProduct.load(product.image) {
                    crossfade(true)
                }
                root.setOnClickListener {
                    onClick(product)
                }

                btnBuy.setOnClickListener{
                    onBuyClick(product)
                }
            }
        }
    }


}
package com.geeks.shopapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.geeks.shopapp.data.model.ProductDto
import com.geeks.shopapp.databinding.ItemProductBinding

class ProductAdapter(
    private val onClick: (ProductDto) -> Unit
) : ListAdapter<ProductDto, ProductAdapter.ProductViewHolder>(ProductDiffUtilCallback()) {

    class ProductDiffUtilCallback: DiffUtil.ItemCallback<ProductDto>() {
        override fun areItemsTheSame(
            oldItem: ProductDto,
            newItem: ProductDto
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductDto,
            newItem: ProductDto
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
        fun bind(product: ProductDto) {
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
            }
        }
    }


}
package com.geeks.shopapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.geeks.shopapp.data.model.ProductDto
import com.geeks.shopapp.databinding.ItemProductBinding

class ProductAdapter(
    private val onClick: (ProductDto) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var items: List<ProductDto> = emptyList()

    fun submitList(list: List<ProductDto>) {
        items = list
        notifyDataSetChanged()
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


    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductDto) {
            with(binding) {
                tvTitle.text = product.title
                tvPrice.text = "${product.price}$"
                tvCategory.text = product.category

                ivProduct.load(product.image) {
                    crossfade(true)  //плавное появление
                }
                root.setOnClickListener {
                    onClick(product)
                }
            }
        }
    }


}
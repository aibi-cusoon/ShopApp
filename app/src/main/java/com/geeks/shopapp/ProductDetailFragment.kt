package com.geeks.shopapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil3.load
import com.geeks.shopapp.data.api.RetrofitService
import com.geeks.shopapp.databinding.FragmentProductDetailBinding
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = args.productId

        viewLifecycleOwner.lifecycleScope.launch {
            val product = RetrofitService.api.getProductsById(productId)
            val rate = product.rating?.rate ?: 0.0
            val count = product.rating?.count ?: 0
            binding.tvTitle.text = product.title
            binding.tvDesc.text = product.description
            binding.tvRate.text = String.format("%.1f", rate)
            binding.tvCount.text = "$count ratings"
            binding.tvPrice.text = "${product.price}$"
            binding.ivProduct.load(product.image)
            binding.tvCategory.text = product.category

            binding.ratingBar.rating = rate.toFloat()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
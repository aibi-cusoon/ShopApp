package com.geeks.shopapp.ui.fragments.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil3.load
import com.geeks.shopapp.databinding.FragmentProductDetailBinding
import com.geeks.shopapp.domain.models.Product
import com.geeks.shopapp.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailFragmentArgs by navArgs()
    private val viewModel: ProductDetailViewModel by viewModel()



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
        viewModel.loadProductById(productId)
        observeState()

    }
    private fun bindProduct(product: Product) {
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

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is UiState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is UiState.Success -> {
                            binding.progressBar.isVisible = false
                            bindProduct(state.data)
                        }

                        is UiState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(
                                requireContext(),
                                state.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
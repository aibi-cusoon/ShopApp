package com.geeks.shopapp.ui.fragments.product

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.shopapp.databinding.FragmentProductListBinding
import com.geeks.shopapp.ui.adapters.ProductAdapter
import com.geeks.shopapp.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by viewModel()

    private val productAdapter = ProductAdapter(
        onClick = { product ->
            val action =
                ProductListFragmentDirections
                    .actionProductListFragmentToProductDetailFragment(product.id)

            findNavController().navigate(action)
        },
        onBuyClick = { product ->
            viewModel.addToCart(product)
            Toast.makeText(requireContext(), "Добавлено!", Toast.LENGTH_SHORT).show()
        }
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeState()

    }

    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = productAdapter
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is UiState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.recyclerView.isVisible = false
                        }

                        is UiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerView.isVisible = true
                            productAdapter.submitList(state.data)
                        }

                        is UiState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerView.isVisible = false
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
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
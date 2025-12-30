package com.geeks.shopapp.ui.fragments.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.shopapp.databinding.FragmentCartBinding
import com.geeks.shopapp.ui.adapters.CartAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModel()
    private val adapter = CartAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        observeData()
        checkout()
    }

    private fun setupRecycler() {
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter
    }

    private fun checkout(){
        binding.btnCheckout.setOnClickListener {
            viewModel.checkout()
        }
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.items.collect { items ->
                Log.d("CartFragment", "Cart items: $items")
                adapter.submitList(items)
                binding.btnCheckout.isEnabled = items.isNotEmpty()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.total.collect { sum ->
                binding.tvTotalValue.text = "$ %.2f".format(sum)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
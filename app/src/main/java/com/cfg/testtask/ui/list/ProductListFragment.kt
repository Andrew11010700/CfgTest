package com.cfg.testtask.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cfg.testtask.R
import com.cfg.testtask.base.BaseFragment
import com.cfg.testtask.databinding.FragmentProductsListBinding
import com.cfg.testtask.ui.list.adapter.ProductAdapter
import com.cfg.testtask.utils.extension.dataBinding
import com.cfg.testtask.utils.extension.launchInViewLifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment: BaseFragment(R.layout.fragment_products_list) {

    override val viewModel: ProductListViewModel by viewModels()

    private val binding: FragmentProductsListBinding by dataBinding()

    private val productAdapter = ProductAdapter { product ->
        viewModel.onProductClick(product)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProductsList()
        observeProductsList()
    }

    private fun setupProductsList() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }

    private fun observeProductsList() {
        launchInViewLifecycleOwner {
            viewModel.productsListResult.collect { products ->
                productAdapter.submitList(products)
            }
        }
    }

}

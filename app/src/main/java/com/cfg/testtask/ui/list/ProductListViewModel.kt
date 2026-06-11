package com.cfg.testtask.ui.list

import com.cfg.testtask.base.BaseViewModel
import com.cfg.testtask.base.events.NavAction
import com.cfg.testtask.domain.model.Product
import com.cfg.testtask.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    repository: ProductRepository
): BaseViewModel() {

    val productsListResult = repository.loadProducts()

    fun onProductClick(product: Product) {
        navigate(NavAction.ToDirection(ProductListFragmentDirections.toEditProductFragment(product.productId)))
    }

}

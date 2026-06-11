package com.cfg.testtask.ui.load

import com.cfg.testtask.base.BaseViewModel
import com.cfg.testtask.base.events.NavAction
import com.cfg.testtask.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoadProductsViewModel @Inject constructor(
    private val repository: ProductRepository
): BaseViewModel() {

    fun loadAllData() {
        launchWithHandler {
            repository.fetchProducts()
            navigate(NavAction.ToDirection(LoadProductsFragmentDirections.toProductListFragment()))
        }
    }

}

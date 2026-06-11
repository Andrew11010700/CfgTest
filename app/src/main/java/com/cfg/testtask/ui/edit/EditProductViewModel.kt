package com.cfg.testtask.ui.edit

import com.cfg.testtask.R
import com.cfg.testtask.base.BaseViewModel
import com.cfg.testtask.base.events.MessageEvent
import com.cfg.testtask.base.events.NavAction
import com.cfg.testtask.data.local.ProductEntity
import com.cfg.testtask.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(
    private val repository: ProductRepository
): BaseViewModel() {

    private val _productState = MutableStateFlow<ProductEntity?>(null)
    val productState = _productState.asStateFlow()

    var initialFieldsState = InitialFieldsState()
        private set

    fun getProductById(id: Long) {
        launchWithHandler(showLoading = false) {
            val product = repository.getProductById(id)
            initialFieldsState.name = product?.name ?: ""
            initialFieldsState.description = product?.description ?: ""
            _productState.emit(product)
        }
    }

    fun onEditProduct(name: String, description: String) {
        launchWithHandler(showLoading = false) {
            val product = productState.value
            product?.name = name
            product?.description = description
            product ?: return@launchWithHandler
            repository.saveProduct(product)
            showMessage(MessageEvent.Success(res.getString(R.string.updated_data)))
            navigate(NavAction.NavigationUp)
        }
    }

}

data class InitialFieldsState(
    var name: String = "",
    var description: String = ""
)
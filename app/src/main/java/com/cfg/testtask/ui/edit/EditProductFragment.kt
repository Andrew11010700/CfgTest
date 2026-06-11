package com.cfg.testtask.ui.edit

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.cfg.testtask.R
import com.cfg.testtask.base.BaseFragment
import com.cfg.testtask.databinding.FragmentEditProductBinding
import com.cfg.testtask.utils.extension.dataBinding
import com.cfg.testtask.utils.extension.launchInViewLifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProductFragment: BaseFragment(R.layout.fragment_edit_product) {

    override val viewModel: EditProductViewModel by viewModels()

    private val args: EditProductFragmentArgs by navArgs()

    private val binding: FragmentEditProductBinding by dataBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getProductById(args.productId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProduct()
        with(binding) {
            btnSaveProduct.setOnClickListener {
                viewModel.onEditProduct(etProductName.text.toString(), etProductDescription.text.toString())
            }
            etProductName.addTextChangedListener {
                btnSaveProduct.isEnabled = it.toString() != viewModel.initialFieldsState.name
                        || etProductDescription.text.toString() != viewModel.initialFieldsState.description
            }
            etProductDescription.addTextChangedListener {
                btnSaveProduct.isEnabled = it.toString() != viewModel.initialFieldsState.description
                        || etProductName.text.toString() != viewModel.initialFieldsState.name
            }
        }
    }


    private fun observeProduct() {
        launchInViewLifecycleOwner {
            viewModel.productState.collect { product ->
                product ?: return@collect
                with(binding) {
                    if (etProductName.text?.isBlank() == false) product.name = etProductName.text.toString()
                    if (etProductDescription.text?.isBlank() == false) product.description = etProductDescription.text.toString()
                }
                binding.product = product
            }
        }
    }


}

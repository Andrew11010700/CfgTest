package com.cfg.testtask.ui.load

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cfg.testtask.R
import com.cfg.testtask.base.BaseFragment
import com.cfg.testtask.databinding.FragmentLoadProductsBinding
import com.cfg.testtask.utils.extension.dataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadProductsFragment : BaseFragment(R.layout.fragment_load_products) {

    override val viewModel: LoadProductsViewModel by viewModels()

    private val binding: FragmentLoadProductsBinding by dataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNavigate.setOnClickListener {
            viewModel.loadAllData()
        }
    }
}

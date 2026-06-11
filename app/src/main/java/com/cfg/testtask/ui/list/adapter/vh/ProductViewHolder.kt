package com.cfg.testtask.ui.list.adapter.vh

import androidx.recyclerview.widget.RecyclerView
import com.cfg.testtask.databinding.ItemProductBinding
import com.cfg.testtask.domain.model.Product

class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onItemClick: (Product) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        with(binding) {
            root.setOnClickListener {
                onItemClick(product)
            }
            this.product = product
            executePendingBindings()
        }
    }
}

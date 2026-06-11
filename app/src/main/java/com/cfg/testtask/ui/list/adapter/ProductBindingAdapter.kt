package com.cfg.testtask.ui.list.adapter

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.cfg.testtask.R

@BindingAdapter("productPrice")
fun TextView.setProductPrice(price: Double) {
    text = context.getString(R.string.product_price_format, price)
}

@BindingAdapter("productMetaBrand", "productMetaCategory")
fun TextView.setProductMeta(brand: String, category: String) {
    text = context.getString(R.string.product_meta_format, brand, category)
}

@BindingAdapter("productRating")
fun TextView.setProductRating(rating: Double) {
    text = context.getString(R.string.product_rating_format, rating)
}

@BindingAdapter("productDiscount")
fun TextView.setProductDiscount(discount: Int) {
    text = context.getString(R.string.product_discount_format, discount)
}

@BindingAdapter("productAvailability")
fun TextView.setProductAvailability(isAvailable: Boolean) {
    text = context.getString(
        if (isAvailable) R.string.product_available else R.string.product_unavailable
    )
    setTextColor(
        ContextCompat.getColor(
            context,
            if (isAvailable) R.color.accent_success else R.color.accent_error,
        )
    )
}

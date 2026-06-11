package com.cfg.testtask.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productId: Long,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "unit")
    val unit: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "discount")
    val discount: Int,
    @ColumnInfo(name = "availability")
    val availability: Boolean,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
)

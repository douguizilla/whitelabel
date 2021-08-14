package com.odougle.whitelabel.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odougle.whitelabel.databinding.ItemProductBinding
import com.odougle.whitelabel.domain.model.Product
import com.odougle.whitelabel.util.toCurrency

class ProductsAdapter {

    class ProductsViewHolder(
        private val itemBinding: ItemProductBinding
    ) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(product: Product){
            itemBinding.run {
                //preencher imagem
                Glide.with(itemView)
                    .load(product.imageUrl)
                    .fitCenter()
                    .into(imageProduct)

                textDescription.text = product.description
                textPrice.text = product.price.toCurrency()
            }
        }

        companion object{
            fun create(parent: ViewGroup): ProductsViewHolder{
                val itemBinding = ItemProductBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return ProductsViewHolder(itemBinding)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>(){
            override fun areItemsTheSame(
                oldItem: Product,
                newItem: Product
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Product,
                newItem: Product
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
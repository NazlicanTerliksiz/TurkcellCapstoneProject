package com.nazlican.turkcellcapstoneproject.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazlican.turkcellcapstoneproject.data.model.Product
import com.nazlican.turkcellcapstoneproject.databinding.ItemViewCartBinding
import com.nazlican.turkcellcapstoneproject.util.extension.downloadFromUrl

class CartAdapter() : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val cartProductList = ArrayList<Product>()

    inner class CartViewHolder(private val binding: ItemViewCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartProducts: Product) {
            binding.apply {
                cartProductIv.downloadFromUrl(cartProducts.thumbnail)
                cartProductNameTv.text = cartProducts.title
                priceTv.text = cartProducts.price.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemViewCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartProductList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartProduct = cartProductList[position]
        holder.bind(cartProduct)
    }

    fun updateList(updateList: List<Product>) {
        cartProductList.clear()
        cartProductList.addAll(updateList)
        notifyDataSetChanged()
    }
}
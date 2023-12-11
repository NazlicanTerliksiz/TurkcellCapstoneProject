package com.nazlican.turkcellcapstoneproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazlican.turkcellcapstoneproject.data.model.Products
import com.nazlican.turkcellcapstoneproject.databinding.ItemViewProductBinding
import com.nazlican.turkcellcapstoneproject.util.extension.downloadFromUrl

class ProductAdapter(
    private val onProductClickListener: (Int) -> Unit,
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val productList = ArrayList<Products>()

    inner class ProductViewHolder(val binding: ItemViewProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val binding =
            ItemViewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.binding.ivProduct.downloadFromUrl(product.images.get(0))
        holder.binding.tvProduct.text = product.title
        holder.binding.tvPrice.text = product.price.toString()

        holder.binding.root.setOnClickListener {
            onProductClickListener.invoke(product.id)
        }
    }

    fun updateList(updateList: List<Products>) {
        productList.clear()
        productList.addAll(updateList)
        notifyDataSetChanged()
    }
}

package com.nazlican.turkcellcapstoneproject.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.nazlican.turkcellcapstoneproject.R
import com.nazlican.turkcellcapstoneproject.common.viewBinding
import com.nazlican.turkcellcapstoneproject.databinding.FragmentCartBinding
import com.nazlican.turkcellcapstoneproject.util.extension.gone
import com.nazlican.turkcellcapstoneproject.util.extension.snackbar
import com.nazlican.turkcellcapstoneproject.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private val binding by viewBinding(FragmentCartBinding::bind)
    private val viewModel: CartViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCartProducts()
        cartObserve()

        with(binding) {
            cartAdapter = CartAdapter()
            cartProductRv.adapter = cartAdapter
        }
    }

    private fun cartObserve() = with(binding) {
        viewModel.cartState.observe(viewLifecycleOwner) { state ->
            when (state) {
                CartState.Loading -> {
                    cartProgressBar.visible()
                }

                is CartState.CartProductSuccessState -> {
                    cartProgressBar.gone()
                    cartEmptyIv.gone()
                    cartAdapter.updateList(state.product.get(1).products)
                    Log.d("message", state.product.toString())
                }

                is CartState.EmptyScreen -> {
                    cartProgressBar.gone()
                    cartEmptyIv.visible()
                    cartEmptyTv.visible()
                    cartEmptyTv.text = state.failMessage
                }

                is CartState.ShowPopUp -> {
                    cartProgressBar.gone()
                    view?.snackbar(state.errorMessage)
                }
            }
        }
    }
}
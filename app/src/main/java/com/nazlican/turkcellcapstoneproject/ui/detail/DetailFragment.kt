package com.nazlican.turkcellcapstoneproject.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nazlican.turkcellcapstoneproject.R
import com.nazlican.turkcellcapstoneproject.common.viewBinding
import com.nazlican.turkcellcapstoneproject.databinding.FragmentDetailBinding
import com.nazlican.turkcellcapstoneproject.util.extension.downloadFromUrl
import com.nazlican.turkcellcapstoneproject.util.extension.gone
import com.nazlican.turkcellcapstoneproject.util.extension.snackbar
import com.nazlican.turkcellcapstoneproject.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.id
        viewModel.getDetailProduct(id)
        detailProductObserve()

        with(binding) {
            back.setOnClickListener {
                findNavController().popBackStack()
            }
            AddToCartbutton.setOnClickListener {
                //viewModel.addToCartProduct(AddToCart(id))
            }
        }
    }

    private fun detailProductObserve() = with(binding) {
        viewModel.detailState.observe(viewLifecycleOwner) { state ->
            when (state) {
                DetailState.Loading -> {
                    detailProgressBar.visible()
                }

                is DetailState.SuccessState -> {
                    detailProgressBar.gone()
                    detailProductIv.downloadFromUrl(state.detailResponse.images.get(0))
                    detailProductTitleTv.text = state.detailResponse.title
                    detailProductCategoryNameTv.text = state.detailResponse.category
                    detailProductDescriptionTv.text = state.detailResponse.description
                    detailProductRatingBar.rating = state.detailResponse.rating.toFloat()
                    priceTv.text = state.detailResponse.price.toString()
                }

                is DetailState.SuccessAddToCartState -> {
                    findNavController().popBackStack()
                }

                is DetailState.EmptyScreen -> {
                    detailProgressBar.gone()
                    detailEmptyIv.visible()
                    detailEmptyTv.visible()
                    detailEmptyTv.text = state.failMessage
                }

                is DetailState.ShowPopUp -> {
                    detailProgressBar.gone()
                    view?.snackbar(state.errorMessage)
                }
            }
        }
    }
}
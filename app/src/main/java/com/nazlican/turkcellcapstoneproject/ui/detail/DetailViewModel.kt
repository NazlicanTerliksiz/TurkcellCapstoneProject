package com.nazlican.turkcellcapstoneproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazlican.turkcellcapstoneproject.common.Resource
import com.nazlican.turkcellcapstoneproject.data.model.AddToCart
import com.nazlican.turkcellcapstoneproject.data.model.Cart
import com.nazlican.turkcellcapstoneproject.data.model.Products
import com.nazlican.turkcellcapstoneproject.data.repo.CartRepository
import com.nazlican.turkcellcapstoneproject.data.repo.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository, private val cartRepository: CartRepository) : ViewModel() {

    private var _detailState = MutableLiveData<DetailState>()
    val detailState: LiveData<DetailState> get() = _detailState

    fun getDetailProduct(id: Int) = viewModelScope.launch {
        _detailState.value = DetailState.Loading

        _detailState.value = when (val result = detailRepository.getDetail(id)) {
            is Resource.Success -> DetailState.SuccessState(result.data)
            is Resource.Fail -> DetailState.EmptyScreen(result.failMessage)
            is Resource.Error -> DetailState.ShowPopUp(result.errorMessage)
        }
    }
    fun addToCartProduct(addToCart: AddToCart) = viewModelScope.launch {
        _detailState.value = DetailState.Loading

        _detailState.value = when (val result = cartRepository.addToCart(addToCart)) {
            is Resource.Success -> DetailState.SuccessAddToCartState(result.data)
            is Resource.Fail -> DetailState.ShowPopUp(result.failMessage)
            is Resource.Error -> DetailState.ShowPopUp(result.errorMessage)
        }
    }

}
sealed interface DetailState {
    object Loading : DetailState
    data class SuccessState(val detailResponse: Products) : DetailState
    data class SuccessAddToCartState(val cart: Cart) : DetailState
    data class EmptyScreen(val failMessage: String) : DetailState
    data class ShowPopUp(val errorMessage: String) : DetailState
}
package com.nazlican.turkcellcapstoneproject.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nazlican.turkcellcapstoneproject.R
import com.nazlican.turkcellcapstoneproject.common.viewBinding
import com.nazlican.turkcellcapstoneproject.databinding.FragmentHomeBinding
import com.nazlican.turkcellcapstoneproject.util.extension.gone
import com.nazlican.turkcellcapstoneproject.util.extension.snackbar
import com.nazlican.turkcellcapstoneproject.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var productAdapter: ProductAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllProducts()
        mainProductObserve()
        search()

        with(binding) {
            productAdapter = ProductAdapter(::homeToDetail)
            rvProducts.adapter = productAdapter
        }
    }

    private fun mainProductObserve() = with(binding) {
        viewModel.homeState.observe(viewLifecycleOwner) { state ->
            when (state) {
                HomeState.Loading -> {
                    homeProgressBar.visible()
                }

                is HomeState.SuccessProductState -> {
                    homeProgressBar.gone()
                    homeEmptyIv.gone()
                    productAdapter.updateList(state.products)
                }

                is HomeState.SuccessSearchState -> {
                    homeProgressBar.gone()
                    productAdapter.updateList(state.products)
                }

                is HomeState.EmptyScreen -> {
                    homeProgressBar.gone()
                    homeEmptyIv.visible()
                    homeEmptyTv.visible()
                    homeEmptyTv.text = state.failMessage
                }

                is HomeState.ShowPopUp -> {
                    homeProgressBar.gone()
                    view?.snackbar(state.errorMessage)
                }
            }
        }
    }
    private fun homeToDetail(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }
    fun search() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchProduct(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.searchProduct(newText)
                return true
            }
        })
    }
}
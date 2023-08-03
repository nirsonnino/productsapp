package com.nino.productsapp.views.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aregyan.github.R
import com.aregyan.github.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private val viewModel: ProductListViewModel by viewModels()

    @Inject
    lateinit var productListAdapter: ProductListAdapter

    @Inject
    lateinit var categoryListAdapter: CategoryListAdapter

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val glm = GridLayoutManager(activity, 2)
        binding.recyclerViewProducts.adapter = productListAdapter
        binding.recyclerViewProducts.layoutManager = glm

        val lm = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCategory.adapter = categoryListAdapter
        binding.recyclerViewCategory.layoutManager = lm

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) { data ->
            val uniqueCategories = data.distinctBy {
                it.category
            }.map { it.category }
            val categories = mutableListOf<String>()
            categories.add("ALL")
            uniqueCategories.forEach { uc ->
                uc?.let { categories.add(it) }
            }
            categoryListAdapter.submitList(categories)
            productListAdapter.submitList(data)
        }

        categoryListAdapter.clickListener.onItemClick = { string ->
            viewModel.getProductsByCategory(string).observe(viewLifecycleOwner) {
                productListAdapter.submitList(it)
            }
        }
        productListAdapter.clickListener.onItemClick = {
            findNavController().navigate(ProductListFragmentDirections.actionProductListToProductDetails(it.title ?: ""))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerViewCategory.adapter = null
        binding.recyclerViewProducts.adapter = null
        _binding = null
    }

}
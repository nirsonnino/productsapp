package com.nino.productsapp.views.productList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.core.widget.NestedScrollView.OnScrollChangeListener
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

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private var limit = 1

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loader.visibility = View.VISIBLE
        viewModel.data.observe(viewLifecycleOwner) { data ->
            productListAdapter.submitList(data)
            binding.loader.visibility = View.GONE
        }

        binding.mainSv.setOnScrollChangeListener(OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                limit++
                val computedLimit = limit * 10

                if (computedLimit == 100) return@OnScrollChangeListener
                viewModel.refreshProducts(computedLimit)
                binding.loader.visibility = View.VISIBLE
            }
        })

        productListAdapter.clickListener.onItemClick = {
            findNavController().navigate(ProductListFragmentDirections.actionProductListToProductDetails(it.title ?: ""))
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                productListAdapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerViewProducts.adapter = null
        _binding = null
    }

}
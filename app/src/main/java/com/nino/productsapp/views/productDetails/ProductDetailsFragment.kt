package com.nino.productsapp.views.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aregyan.github.R
import com.aregyan.github.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private val viewModel: ProductDetailsViewModel by viewModels()
    private val args: ProductDetailsFragmentArgs by navArgs()

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_details, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductDetails(args.title).observe(viewLifecycleOwner) {
            viewModel.productDetails.set(it)
        }

        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
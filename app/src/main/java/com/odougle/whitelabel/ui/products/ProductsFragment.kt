package com.odougle.whitelabel.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import com.odougle.whitelabel.R
import com.odougle.whitelabel.databinding.FragmentProductsBinding
import com.odougle.whitelabel.domain.model.Product
import com.odougle.whitelabel.util.PRODUCT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()

    private val productsAdapter = ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setListeners()
        observeNavBackStack()
        observeVMEvents()
        viewModel.getProducts()
    }

    private fun setRecyclerView() {
        binding.recyclerProducts.run {
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }

    private fun setListeners(){
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_addProductFragment)
        }
    }

    private fun observeNavBackStack(){
        findNavController().run {
            val navBackStackEntry = getBackStackEntry(R.id.productsFragment)
            val savedStateHandle = navBackStackEntry.savedStateHandle
            val observer = LifecycleEventObserver{ _, event ->
                if(event == Lifecycle.Event.ON_RESUME && savedStateHandle.contains(PRODUCT_KEY)){
                    val product = savedStateHandle.get<Product>(PRODUCT_KEY)
                    val oldList = productsAdapter.currentList
                    val newList = oldList.toMutableList().apply {
                        add(product)
                    }
                    productsAdapter.submitList(newList)
                    binding.recyclerProducts.smoothScrollToPosition(newList.size-1)
                    savedStateHandle.remove<Product>(PRODUCT_KEY)
                }
            }
            navBackStackEntry.lifecycle.addObserver(observer)

            viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver{_, event ->
                if(event == Lifecycle.Event.ON_DESTROY){
                    navBackStackEntry.lifecycle.removeObserver(observer)
                }
            })
        }
    }

    private fun observeVMEvents() {
        viewModel.productsData.observe(viewLifecycleOwner) { products ->
            productsAdapter.submitList(products)
        }

        viewModel.addButtonVisibilityData.observe(viewLifecycleOwner){ visibility ->
            binding.fabAdd.visibility = visibility
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
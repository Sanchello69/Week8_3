package com.vas.feature_favorite_screen.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.vas.core.utils.Result
import com.vas.feature_favorite_screen.databinding.FragmentFavoriteBinding
import com.vas.feature_favorite_screen.di.FavoriteComponentViewModel
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: FavoriteViewModelFactory

    private var binding: FragmentFavoriteBinding? = null
    private var viewModel: FavoriteViewModel? = null
    private var favoriteAdapter: FavoriteCatAdapter? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FavoriteComponentViewModel>()
            .newFavoriteComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(FavoriteViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel?.favorites?.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {

                    Log.d("status", "SUCCESS")

                    result.data?.let {
                        favoriteAdapter?.data = it
                    }

                }
                Result.Status.LOADING -> Log.d("status", "LOADING")
                Result.Status.ERROR -> Log.d("status", "ERROR")
            }
        })
    }

    private fun setupUI() {
        initFavoriteRecyclerView()
    }

    private fun initFavoriteRecyclerView() {
        favoriteAdapter = FavoriteCatAdapter()
        binding?.favoriteRecyclerView?.layoutManager = GridLayoutManager(requireContext(), 2)
        binding?.favoriteRecyclerView?.adapter = favoriteAdapter
    }
}
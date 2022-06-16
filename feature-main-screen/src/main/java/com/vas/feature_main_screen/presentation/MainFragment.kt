package com.vas.feature_main_screen.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.vas.core.utils.Status
import com.vas.feature_main_screen.databinding.FragmentMainBinding
import com.vas.feature_main_screen.di.MainComponentViewModel
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.navigation.navigate
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var mainNavCommandProvider: MainNavCommandProvider

    private var binding: FragmentMainBinding? = null
    private var viewModel: MainViewModel? = null
    private var positionViewPager: Int? = null

    private val pagingAdapter by lazy {
        CatListAdapter()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainComponentViewModel>()
            .newMainComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
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

    private fun setupObservers() {
        viewModel?.catListData?.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                pagingAdapter.submitData(it)
            }
        })

        viewModel?.message?.observe(viewLifecycleOwner, Observer {
            if (it=="SUCCESS" || it.contains("DUPLICATE_FAVOURITE"))
                binding?.catViewPager?.currentItem = positionViewPager!!+1
            else
                Toast.makeText(requireContext(), "Повторите попытку!", Toast.LENGTH_SHORT).show()
            Log.d("message_post", "$it")
        })
    }

    private fun setupUI() {
        initCatViewPager()
        initFavoriteButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    private fun initCatViewPager() {

        pagingAdapter.onClickListener = object : CatListAdapter.OnCatClickListener{
            override fun onLikeClick(id: String) {
                likeClick(id)
            }

            override fun onNoLikeClick() {
                noLikeClick()
            }
        }

        binding?.catViewPager?.adapter = pagingAdapter
        binding?.catViewPager?.isUserInputEnabled = false
        binding?.catViewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                positionViewPager = position
                super.onPageSelected(position)
            }
        })
    }

    private fun initFavoriteButton() {
        binding?.starsImageView?.setOnClickListener {
            navigate(mainNavCommandProvider.toFavorite)
        }
    }

    private fun likeClick(id: String){
        Log.d("like", "yes")
        viewModel?.postLike(id)
    }

    private fun noLikeClick(){
        Log.d("like", "no")
        binding?.catViewPager?.currentItem = positionViewPager!!+1
    }
}
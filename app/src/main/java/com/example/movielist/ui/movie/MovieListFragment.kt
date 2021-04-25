package com.example.movielist.ui.movie

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielist.R
import com.example.movielist.data.local.sharedPreference.SharedPreferenceStringLiveData
import com.example.movielist.databinding.MovieListFragmentBinding
import com.example.movielist.util.RecyclerViewPaginator
import com.example.movielist.util.SpacesItemDecoration
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


private const val TAG = "Lifecycle Fragment1"

class MovieListFragment : Fragment() {


    private val movieListViewModel: MovieListViewModel by viewModel()
    private var _movieListFragmentBinding: MovieListFragmentBinding? = null
    private val preference: SharedPreferences by inject()

    private val binding get() = _movieListFragmentBinding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")

        _movieListFragmentBinding = MovieListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        val adapter = MoviePagerAdapter(requireContext())
        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        retainInstance = true

        movieListViewModel.enqueWork()
        movieListViewModel.outputWorkInfos.observe(viewLifecycleOwner) Observer@{


            listOfWorkInfo ->

            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

            // We only care about the one output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
               println("worker finished")

            } else {
            }

        }

        binding.apply {
            rvMovies.setHasFixedSize(true)
            rvMovies.adapter = adapter
            rvMovies.layoutManager = manager
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.corner)
            rvMovies.addItemDecoration(SpacesItemDecoration(spacingInPixels))


            ibtSearch.setOnClickListener {
                val action = MovieListFragmentDirections.actionMovieListFragmentToSearchFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }

            ibtBack.setOnClickListener {

                requireActivity().onBackPressed()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            movieListViewModel.getMoviesList().collectLatest { pagingData ->
                movieListViewModel.setTitle()
                adapter.submitData(pagingData)
            }
        }

        movieListViewModel.updateTile.observe(viewLifecycleOwner) {
            it.let {
                binding.tvTitle.text = it
            }
        }

        val sharedPreferences = SharedPreferenceStringLiveData(preference, "title", "")
        sharedPreferences.observe(viewLifecycleOwner) {

            binding.tvTitle.text = it
        }

        val recyclerViewPaginator = object : RecyclerViewPaginator(recyclerView = binding.rvMovies){
            override val isLastPage: Boolean
                get() = false

            override fun loadMore(start: Long, count: Long) {

            }

        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

        _movieListFragmentBinding = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        setColumns(
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                7
            } else 3
        )
        super.onConfigurationChanged(newConfig)
    }

    private fun setColumns(count: Int) {
        binding.rvMovies.layoutManager =
            GridLayoutManager(activity, count, GridLayoutManager.VERTICAL, false)
    }



}
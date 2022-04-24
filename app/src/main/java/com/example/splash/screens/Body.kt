package com.example.splash.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.splash.adapter.PhotoGridAdapter
import com.example.splash.databinding.FragmentBodyBinding
import com.example.splash.viewmodel.SplashViewModel

/**
 * This fragment shows the status of the Mars photos web services transaction
 */
class Body : Fragment() {

    private val viewModel: SplashViewModel by viewModels()

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBodyBinding.inflate(inflater)

        binding.apply{

            // Allows Data Binding to observe LiveData with the lifeCycle of this fragment
            lifecycleOwner = this@Body

            // Giving the binding access to the SplashViewModel
            viewModel = this@Body.viewModel

            // Sets the adapter of the photosGrid RecyclerView
            photosGrid.adapter = PhotoGridAdapter()
        }
        return binding.root
    }
}
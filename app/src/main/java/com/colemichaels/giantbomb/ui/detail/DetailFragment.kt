package com.colemichaels.giantbomb.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.colemichaels.giantbomb.LOG_TAG
import com.colemichaels.giantbomb.R
import com.colemichaels.giantbomb.databinding.DetailFragmentBinding
import com.colemichaels.giantbomb.ui.shared.SharedViewModel

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var binding: DetailFragmentBinding

    private lateinit var viewModel: SharedViewModel

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setTitle(viewModel.selectedGame.value?.name)
        }

        setHasOptionsMenu(true)

        binding = DetailFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}
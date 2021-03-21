package com.colemichaels.giantbomb.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.colemichaels.giantbomb.LOG_TAG
import com.colemichaels.giantbomb.R
import com.colemichaels.giantbomb.data.models.Game
import com.colemichaels.giantbomb.data.models.GiantBombResponse
import com.colemichaels.giantbomb.databinding.MainFragmentBinding
import com.colemichaels.giantbomb.ui.shared.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainFragment : Fragment(),
    MainRecyclerAdapter.GameItemListener,
    SearchView.OnQueryTextListener {

    companion object {
        fun newInstance() = MainFragment()
        const val ON_QUERY_DELAY = 500L
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: SharedViewModel

    private lateinit var navController: NavController

    private lateinit var searchView: SearchView

    private var queryTextChangedJob: Job? = null

    private var queryString: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setTitle("")
        }

        setHasOptionsMenu(true)

        binding = MainFragmentBinding.inflate(inflater, container, false)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        viewModel.giantBombData.observe(viewLifecycleOwner, {
            binding.giantBombRecycler.adapter = MainRecyclerAdapter(it.results, this)

            binding.giantBombSwipeLayout.isRefreshing = false

            handleMainFragmentLayout(it)
        })

        binding.giantBombSwipeLayout.setOnRefreshListener {
            viewModel.refreshData(queryString)
        }

        return binding.root
    }

    private fun handleMainFragmentLayout(giantBombResponse: GiantBombResponse) {
        if (giantBombResponse.results.isEmpty()) {
            binding.noContent.visibility = View.VISIBLE

            binding.giantBombSwipeLayout.visibility = View.GONE
        } else {
            binding.noContent.visibility = View.GONE

            binding.giantBombSwipeLayout.visibility = View.VISIBLE
        }

        binding.progressBar.visibility = View.GONE
    }

    override fun onGameItemClick(gameItem: Game) {
        Log.i(LOG_TAG, gameItem.name.toString())

        viewModel.selectedGame.value = gameItem

        navController.navigate(R.id.action_nav_detail)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)

        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

        searchView.setOnQueryTextListener(this)

        searchView.queryHint = "Search for a game..."

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> false
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.i(LOG_TAG, "onQueryTextSubmit: ${query.toString()}")

        if (query == null) return true

        queryString = query

        binding.noContent.visibility = View.GONE

        viewModel.refreshData(query)

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText == null || newText == "") return false

        queryString = newText

        binding.noContent.visibility = View.GONE

        binding.progressBar.visibility = View.VISIBLE

        queryTextChangedJob?.cancel()

        queryTextChangedJob = lifecycleScope.launch(Dispatchers.Main) {
            Log.i(LOG_TAG, "onQueryTextSubmit: Async work started...")

            delay(ON_QUERY_DELAY)

            viewModel.refreshData(queryString)

            Log.i(LOG_TAG, "onQueryTextSubmit: Async work done!")
        }

        return false
    }

}


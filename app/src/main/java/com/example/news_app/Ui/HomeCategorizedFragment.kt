package com.example.news_app.Ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.news_app.Model.CategoryAdapter
import com.example.news_app.Model.PostAdapter
import com.example.news_app.R
import com.example.news_app.Model.SourceAdapter
import com.example.news_app.Model.SourceData
import com.example.news_app.ViewModel.API_ViewModel
import com.example.news_app.databinding.FragmentHomeCategorized2Binding
import kotlinx.coroutines.launch

class HomeCategorizedFragment : Fragment() {

    lateinit var binding: FragmentHomeCategorized2Binding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    private val viewModel = API_ViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeCategorized2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.tvTitle.text= CategoryAdapter.category
        navController = Navigation.findNavController(view)
        binding.navView.setupWithNavController(navController)
        lifecycleScope.launch {
            actionBarDrawerToggle = ActionBarDrawerToggle(
                activity,
                binding.drawerLayout,
                R.string.nav_open,
                R.string.nav_close
            )
            binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
            actionBarDrawerToggle.syncState()
            binding.include.menuIcon.setOnClickListener {
                binding.drawerLayout.openDrawer(binding.navView)
            }

            binding.navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.homeFragment -> navController.navigate(R.id.action_homeCategorizedFragment_to_homeFragment)
                    R.id.settingsFragment-> navController.navigate(R.id.action_homeCategorizedFragment_to_settingsFragment)
                }
                menuItem.isChecked = true
                binding.drawerLayout.close()
                true
            }
            binding.include.searchIcon.setOnClickListener {
                Navigation.createNavigateOnClickListener(R.id.action_homeCategorizedFragment_to_homSearchFragment).onClick(view)
            }
        }
        lifecycleScope.launch {
            //sources
            viewModel.sources(CategoryAdapter.category).getSources()
                .observe(viewLifecycleOwner, Observer { sources ->
                    if(sources.sources.size!=0) {
                        SourceAdapter.sourceId = sources.sources[0].id.toString()
                        val adapter = SourceAdapter(sources.sources)
                        binding.sourceRecyclerview.adapter = adapter
                        adapter.setOnClickListener(object : SourceAdapter.OnClickListener {
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onClick(position: Int, model: SourceData) {
                                SourceAdapter.sourceId = sources.sources[position].id.toString()
                                viewModel.Posts(SourceAdapter.sourceId).getPosts()
                                adapter.notifyDataSetChanged()
                            }
                        })
                        //posts
                        viewModel.Posts(SourceAdapter.sourceId).getPosts()
                            .observe(viewLifecycleOwner, Observer { posts ->
                                if(posts.articles.size!=0) {
                                    val adapter = PostAdapter(posts.articles)
                                    binding.articlesRecyclerview.adapter = adapter
                                }
                            })
                    }
                })
        }
    }
}
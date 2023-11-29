package com.example.news_app.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.news_app.Model.Category
import com.example.news_app.Model.Category2Adapter
import com.example.news_app.Model.CategoryAdapter
import com.example.news_app.R
import com.example.news_app.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       FragmentHomeBinding.bind(view)
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
                // Handle menu item selected
                when (menuItem.itemId) {
                    R.id.nav_home -> navController.navigate(R.id.action_homeFragment_self)
                    R.id.nav_setting -> navController.navigate(R.id.action_homeFragment_to_settingsFragment)
                }
                menuItem.isChecked = true
                binding.drawerLayout.close()
                true
            }
        }
        //ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,binding.drawerLayout,binding.include.menuIcon)
        val categoryList1=ArrayList<Category>()
        val categoryList2=ArrayList<Category>()
        categoryList1.add(Category(R.drawable.sports,"Sports"))
        categoryList1.add(Category(R.drawable.health,"Health"))
        categoryList1.add(Category(R.drawable.environment,"Environment"))
        categoryList2.add(Category(R.drawable.politics,"Politics"))
        categoryList2.add(Category(R.drawable.bussines,"Bussines"))
        categoryList2.add(Category(R.drawable.science,"Science"))
        binding.categoryRecyclerView.adapter= CategoryAdapter(categoryList1)
        binding.categoryRecyclerView2.adapter= Category2Adapter(categoryList2)
        //Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_homSearchFragment).onClick(view)
    }

}
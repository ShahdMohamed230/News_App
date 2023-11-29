package com.example.news_app.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.news_app.R
import com.example.news_app.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentSettingsBinding.bind(view)
        binding.include1.tvTitle.text="Settings"
        val lang= arrayOf("English","Arabic")
        val spinner_adapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,lang)
        binding.langSpinner.adapter=spinner_adapter
        binding.langSpinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
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
            binding.include1.menuIcon.setOnClickListener {
                binding.drawerLayout.openDrawer(binding.navView)
            }

            binding.navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_home -> navController.navigate(R.id.action_settingsFragment_to_homeFragment)
                    R.id.nav_setting -> navController.navigate(R.id.action_settingsFragment_self)
                }
                menuItem.isChecked = true
                binding.drawerLayout.close()
                true
            }
        }
    }
}
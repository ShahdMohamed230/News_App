package com.example.news_app.Ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.news_app.R
import com.example.news_app.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch
import java.util.Locale

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
    companion object
    {
        var lan="en"
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentSettingsBinding.bind(view)
        binding.include1.tvTitle.text="Settings"
        val lang:Array<String>
        if(lan=="en") {
             lang = arrayOf("English", "Arabic")
        }
        else
        {
            lang = arrayOf("لغة إنجليزية", "لغة عربية")
        }
        val spinner_adapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,lang)
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.langSpinner.adapter=spinner_adapter
        binding.langSpinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var selectedLang=parent!!.getItemAtPosition(position).toString()
                if(selectedLang=="English")
                {
                    lan="en"
                }
                else
                {
                    lan="ar"
                }
               // setLocate(lan)
                recreate(requireActivity())
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
                    R.id.homeFragment -> navController.navigate(R.id.action_settingsFragment_to_homeFragment)
                    R.id.settingsFragment -> navController.navigate(R.id.action_settingsFragment_self)
                }
                menuItem.isChecked = true
                binding.drawerLayout.close()
                true
            }
        }
    }
}
/*private fun setLocate(lang:String)
{
    val locale=Locale(lang)
    Locale.setDefault(locale)
    val config=Configuration()
    config.locale=locale
}*/
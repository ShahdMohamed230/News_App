package com.example.news_app.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_app.Model.ResultAdapter
import com.example.news_app.ViewModel.API_ViewModel
import com.example.news_app.databinding.FragmentHomSearchBinding
import kotlinx.coroutines.launch

class HomSearchFragment : Fragment() {
    lateinit var binding: FragmentHomSearchBinding
    private lateinit var navController: NavController
    private val viewModel = API_ViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentHomSearchBinding.bind(view)
        lifecycleScope.launch {
            binding.include.searchArticle.setOnClickListener()
            {
                if (binding.include.searchArticle.query.isNotEmpty()) {
                    val q = binding.include.searchArticle.query.toString()
                    viewModel.Results(q).getResult()
                        .observe(viewLifecycleOwner, Observer { results ->
                            if (results.articles.size > 0) {
                                val adapter = ResultAdapter(results.articles)
                                binding.articlesRecyclerview.adapter = adapter
                            }
                        })
                }
            }

        }
    }
}
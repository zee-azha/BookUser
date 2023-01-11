package com.example.bookUser

import ListContactAdapter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookUser.ViewModel.MainViewModel
import com.example.bookUser.databinding.ActivityMainBinding


import com.example.bookUser.ViewModel.ViewModelFactory
import com.example.bookUser.data.ResultBooks


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  val viewModel: MainViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
    private lateinit var adapter: ListContactAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListContactAdapter()
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter= adapter
        }


        viewModel.list.observe(this){
        adapter.setData(it as ArrayList<ResultBooks>)
        }
        getBook()

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(title: String?): Boolean {
                getOnSearch()
                return true
            }

            override fun onQueryTextChange(title: String?): Boolean {
               return false
            }

        })

    }

    fun getOnSearch(){
        val title = binding.search.query.toString()
            viewModel.searchBook(title)
        }




    private fun getBook(){
        viewModel.getBook()
    }


}
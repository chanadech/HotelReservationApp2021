package com.example.dechproduct.hotelreservationapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dechproduct.hotelreservationapp.data.util.Resource
import com.example.dechproduct.hotelreservationapp.databinding.FragmentNewsBinding
import com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search.SearchReservationActivity
import com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search.SearchReservationViewModel
import com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search.adapter.NewsAdapter
//
//class NewsFragment : Fragment() {
//
//    private lateinit var viewModel: SearchReservationViewModel
//    private lateinit var fragmentNewsBinding: FragmentNewsBinding
//    private lateinit var newsAdapter: NewsAdapter
//    private val country = "us"
//    private val page = 1
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_news, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        fragmentNewsBinding = FragmentNewsBinding.bind(view)
//        viewModel = (activity as SearchReservationActivity).viewModel
//        initRecyclerView()
//        viewNewsList()
//    }
//
//    private fun viewNewsList() {
//        viewModel.getNewsHeadLines(country,page)
//        viewModel.newsHeadlines.observe(viewLifecycleOwner, {response ->
//            when (response) {
//                is Resource.Success -> {
//                    hideProgressBar()
//                    response.data?.let{
//                        Log.i("MYTAG","came here za ${it.articles.toList().size}")
//                        newsAdapter.differ.submitList(it.articles.toList())
//                    }
//                }
//                is Resource.Error -> {
//                    hideProgressBar()
//                    response.message?.let{
//                        Toast.makeText(activity,"An error occurred: $it", Toast.LENGTH_LONG).show()
//                    }
//                }
//                is Resource.Loading -> {
//                    showProgressBar()
//                }
//
//
//            }
//
//        })
//    }
//
//    private fun initRecyclerView() {
//        newsAdapter = NewsAdapter()
//        fragmentNewsBinding.rvNews.apply {
//            adapter = newsAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
//        //or
//        /*
//        newsAdapter = NewsAdapter()
//        fragmentNewsBinding.rvNews.adapter = newsAdapter
//        fragmentNewsBinding.rvNews.layoutManager = LinearLayoutManager(
//            activity
//        )
//        */
//    }
//    private fun showProgressBar(){
//        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
//    }
//
//    private fun hideProgressBar(){
//        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
//    }
//
//
//}



class NewsFragment : Fragment() {
    private  lateinit var viewModel: SearchReservationViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private var country = "us"
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel= (activity as SearchReservationActivity).viewModel
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {

        viewModel.getNewsHeadLines(country,page)
        viewModel.newsHeadlines.observe(viewLifecycleOwner,{response->
            when(response){
                is Resource.Success->{

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG","came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity,"An error occurred : $it", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading->{
                    showProgressBar()
                }

            }
        })
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar(){
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }

}
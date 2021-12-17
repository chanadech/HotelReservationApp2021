package com.example.dechproduct.hotelreservationapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.util.Resource
import com.example.dechproduct.hotelreservationapp.databinding.FragmentNewsBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.reservation_search.SearchReservationActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.reservation_search.SearchReservationViewModel
import com.example.dechproduct.hotelreservationapp.presentation.reservation.reservation_search.adapter.NewsAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    private var isScrolling: Boolean = false
    private var isLoading = false
    private var isLastPages = false
    private var pages = 0
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
        newsAdapter = (activity as SearchReservationActivity).newsAdapter
        newsAdapter.setOnItemClickListener { //allow to get selected news article instance -> make article obj serializable
        // -> implement selialize interface -> Article Model class -> class xx(){}): Serializable <--here -> can write code to get the selected artivle obj to bundle and pass it fo navigate laew
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
                //create code to navigate from news fragment to info fragment using nav Controller
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
            //bundle // need to add argument to info fragment -> nav graph -> infofragment -> click to add argument
        }
        initRecyclerView()
        viewNewsList()
        setSearchView()
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
                        if(it.totalResults%20 == 0){
                         pages = it.totalResults/20
                        } else {
                            pages = it.totalResults/20+1
                        }
                        isLastPages = page == pages

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
//        newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun showProgressBar(){
        isLoading = true
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){    // check ว่าโดน scroll อยู๋บ่
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager // จะเอา 3 property ของ recycler view ตอนนี้ -> size ของ current ;ist
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            //The current list has to reach to the last item before we do pagination -> check using topposition, visibleitem, sizeofCurrentList
            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList

            // if data ยังโหลดอยู๋ / last page ->ไม่ต้อง paginate -> ให้ reached to the end -> user still scroll the list
            //pagination happen -> page number increase by 1 -> invoke getNewsHeadLines function of viewModel โดยใช้ new page number -> set isScroll = false
            // include addOnScrollListener to initRecyclerView

            val shouldPaginate = !isLoading && !isLastPages && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                page++
                viewModel.getNewsHeadLines(country, page)
                isScrolling = false
            }
        }
    }
    // when user type enter  button of keyboard
    // we need to write codes to invoke view model search news function passong the typed search query
    //  and call view search news function that created to display observe result on recyclerview
    private  fun setSearchView(){
        fragmentNewsBinding.svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.searchNews("us",p0.toString(), page)
                viewSearchedNews()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {      // will be invoke for each text change in search view -> execute when text typed
                MainScope().launch {
                    delay(2000)
                    viewModel.searchNews("us",p0.toString(), page)
                    viewSearchedNews()
                }

                return false
            }
        }
        )
       fragmentNewsBinding.svNews.setOnCloseListener (object : SearchView.OnCloseListener {
           override fun onClose(): Boolean {
               initRecyclerView()
               viewNewsList()
               return false
           }
       })
    }

    // search -> display as a normal list -> observe searchnews  live data from viewmodel and pass this list to recyclerview adapter
    // same as viewnewlist function -> change newsHeadlines to searched news -> create fnction to get the search query
    // need to implement the setOnQuryTextListener of search view -
    fun viewSearchedNews(){
//        viewModel.getNewsHeadLines(country,page)
        //if ( view != null){ // careful here
        if(view != null){
        viewModel.searchedNews.observe(viewLifecycleOwner,{ response->
            when(response){
                is Resource.Success->{

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG","came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                        if(it.totalResults%20 == 0){
                            pages = it.totalResults/20
                        } else {
                            pages = it.totalResults/20+1
                        }
                        isLastPages = page == pages

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
        //}
        }
    }

}


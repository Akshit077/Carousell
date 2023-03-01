package com.example.carousell

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carousell.api.ApiInterface
import com.example.carousell.api.DataModel
import com.example.carousell.api.RetrofitService
import com.example.carousell.databinding.FragmentArticlesBinding

class ArticlesFragment : Fragment(),ArticlesAdapter.OnItemClickListener {

    private lateinit var progressDialog:ProgressDialog
    private lateinit var recyclerView: RecyclerView
    lateinit var articleViewModel: ArticlesViewModel
    private var itemAdapter = ArticlesAdapter(context,this)
    private var newsList =  ArrayList<DataModel>()
    private lateinit var binding: FragmentArticlesBinding
    private lateinit var toolbar: Toolbar
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_articles, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles, container, false)
        toolbar = binding.toolbar
        toolbar.title = "Carousell"
        toolbar.setCollapseIcon(R.drawable.ic_baseline_more_horiz_24)
        recyclerView = binding.recyclerView
        articleViewModel = ViewModelProvider(this, MyViewModelFactory(ArticleRepository(retrofitService))).get(ArticlesViewModel::class.java)
        return root
    }

    override fun onResume() {
        super.onResume()
        setupUI()
//        createProgressDialog()
        fetchArticles()
    }

    private fun setupUI() {
//        val layoutManager = LinearLayoutManager(activity , LinearLayoutManager.VERTICAL,false)
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = itemAdapter
    }


    private fun fetchArticles() {
//        progressDialog.show()
        val layoutManager = LinearLayoutManager(activity , LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = itemAdapter
        articleViewModel.newsList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            itemAdapter.setMovieList(it)
//            newsList.addAll(it)
//            itemAdapter =
//                ArticlesAdapter(context, newsList, this)
//            recyclerView.adapter = itemAdapter
//            recyclerView.adapter?.notifyDataSetChanged()
        })
        articleViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onError: $it")
        })

        articleViewModel.getAllArticles()
    }


    private fun createProgressDialog() {
        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Loading..")
        progressDialog.setMessage("Please wait while we fetch data..")
        progressDialog.setCancelable(false)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            ArticlesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemClick(
        position: Int,
        url_adapter: String,
        title_adapter: String,
        desc_adapter: String
    ) {
        Log.d("Item click",title_adapter)
    }
}
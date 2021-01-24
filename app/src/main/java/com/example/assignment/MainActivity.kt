package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var itemList= arrayListOf<LanguageData.Items>()
    private lateinit var recyclerAdapter: LanguageAdapter
    private lateinit var recyclerlayout:RecyclerView.LayoutManager
    lateinit var progressBar: ProgressBar
    lateinit var mainRecycler:RecyclerView
    lateinit var mainSearch:SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   //     val mainActivityDataBinding: com.example.assignment.databinding.ActivityMainBinding   = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainSearch=findViewById(R.id.mainSearch)
        mainRecycler=findViewById(R.id.mainRecycler)
        progressBar=findViewById(R.id.mainProgress)
        mainSearch.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val query = "language:"+ p0!!
                progressBar.visibility=View.VISIBLE
                getRepoData(query)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               return false
            }

        })
        if(Constants.objects.isEdited){
            recyclerlayout = LinearLayoutManager(this@MainActivity)
            recyclerAdapter = LanguageAdapter(this@MainActivity, Constants.objects.list)
            mainRecycler.layoutManager = recyclerlayout
            mainRecycler.adapter = recyclerAdapter
        }/*else {
            getRepoData("language:python")
        }*/


    }
    private fun getRepoData(q:String){
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.objects.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api=retrofit.create(API::class.java)
        val call = api.getRepository(q)
        call.enqueue(object : Callback<LanguageData> {
            override fun onResponse(call: Call<LanguageData>, response: Response<LanguageData>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT)
                            .show()
                    return
                }
                progressBar.visibility=View.GONE
                val data = response.body()
                if (data != null) {
                     for (i in data.items){
                   itemList.add(i)
                    }
        //            itemList = data.items
                    Constants.objects.list=itemList
                    recyclerlayout = LinearLayoutManager(this@MainActivity)
                    recyclerAdapter = LanguageAdapter(this@MainActivity, itemList)
                    mainRecycler.layoutManager = recyclerlayout
                    mainRecycler.adapter = recyclerAdapter
                }
            }

            override fun onFailure(call: Call<LanguageData>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })

    }

}
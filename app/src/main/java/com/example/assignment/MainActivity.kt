package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val baseURL="https://api.github.com/"
    var itemList= arrayListOf<LanguageData.Items>()
    private lateinit var recyclerAdapter: LanguageAdapter
    private lateinit var recyclerlayout:RecyclerView.LayoutManager
    lateinit var mainRecycler:RecyclerView
    lateinit var mainSearch:SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   //     val mainActivityDataBinding: com.example.assignment.databinding.ActivityMainBinding   = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainSearch=findViewById(R.id.mainSearch)
        mainRecycler=findViewById(R.id.mainRecycler)
        getRepoData("language:python")




    }
    fun getRepoData(q:String){
        val retrofit= Retrofit.Builder()
            .baseUrl(baseURL)
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
                val data = response.body()
                if (data != null) {
                    /* for (i in data.items){
                   itemList.add(i)
                    }*/
                    itemList = data.items
            println("item list is"+itemList.size.toString())
                    Toast.makeText(this@MainActivity,itemList.size.toString(),Toast.LENGTH_LONG).show()
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
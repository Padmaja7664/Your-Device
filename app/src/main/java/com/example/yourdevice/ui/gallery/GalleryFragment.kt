package com.example.yourdevice.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourdevice.ApiInterface
import com.example.yourdevice.MyAdapter
import com.example.yourdevice.MyData
import com.example.yourdevice.databinding.FragmentGalleryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GalleryFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        recyclerView=binding.recycler

        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitdata=retrofitBuilder.getProductData()

        retrofitdata.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if api  call is sucessful

                val responseBody=response.body()
                val productlist=responseBody?.products!!
                myAdapter= MyAdapter(this@GalleryFragment,productlist)
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager= LinearLayoutManager(context)



            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if api call is failed
                Log.d("Gallery Fragment","onFailure: "+t.message)
            }
        })


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
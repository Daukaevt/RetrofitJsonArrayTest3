package com.wixsite.mupbam1.resume.retrofitjsonarraytest3

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.wixsite.mupbam1.resume.retrofitjsonarraytest3.databinding.ActivityMainBinding
import com.wixsite.mupbam1.resume.retrofitjsonarraytest3.retrofit_api.RetrofitAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException



class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        parseJSON()

    }
    fun parseJSON() {
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(RetrofitAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            // Do the GET request and get response
            val response = service.getEmployees()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val items = response.body()
                    val dataList1=ArrayList<String>()
                    if (items != null) {
                        for (i in 0 until items.count()) {
                            // title
                            val title = items[i].title ?: "N/A"
                          //  Log.d("MyLog", "title: $title")
                            dataList1.add(title)
                            // url
                            val url1 = items[i].url ?: "N/A"
                            //Log.d("MyLog", "url: $url1")
                        }
                    } else {
                        Log.e("RETROFIT_ERROR", response.code().toString())
                    }
                    Log.d("MyLog","data: $dataList1")
                    binding.textView3.text=dataList1.toString()
                    /*
                    тоже работает, но долго
                    for (item in 0..dataList1.size-1){

                        var text2=dataList1[item]
                        var text1=binding.textView3.text
                        var text3="$text1 $text2"
                        binding.textView3.text=text3
                        Log.d("MyLog","$item")
                    }

                     */
                }
            }
        }
       // Log.d("MyLog", "title: $dataList")
    }
}
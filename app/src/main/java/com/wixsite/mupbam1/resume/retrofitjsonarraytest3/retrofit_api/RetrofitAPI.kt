package com.wixsite.mupbam1.resume.retrofitjsonarraytest3.retrofit_api

import com.wixsite.mupbam1.resume.retrofitjsonarraytest3.data.DrinkX
import com.wixsite.mupbam1.resume.retrofitjsonarraytest3.data.PhotoItem
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("/photos")
    suspend fun getEmployees(): Response<List<PhotoItem>>
}
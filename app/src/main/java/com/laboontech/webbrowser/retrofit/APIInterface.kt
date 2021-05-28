package com.laboontech.webbrowser.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("fetch_home_data.php")
    fun getHomeData():Call<ResponseBody>


    @GET("fetch_sliders.php")
    fun imageSliderData():Call<ResponseBody>






}
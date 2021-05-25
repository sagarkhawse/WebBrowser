package com.laboontech.webbrowser.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {




    @GET("matchCalendar?apikey=KOqZMGQcDYO6l9oEA6GN3VPJFum2")
    fun upcomingmatches():Call<ResponseBody>



    @GET("fetch_home_data.php")
    fun getHomeData():Call<ResponseBody>



    @GET("fetch_memes.php")
    fun memesList():Call<ResponseBody>



    @GET("cricketScore?apikey=KOqZMGQcDYO6l9oEA6GN3VPJFum2")
    fun livescore( @Query("unique_id") unique_id: String):Call<ResponseBody>


}
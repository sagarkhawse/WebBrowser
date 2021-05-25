package com.laboontech.webbrowser.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laboontech.webbrowser.R
import com.laboontech.webbrowser.adapter.HomeAdapter
import com.laboontech.webbrowser.retrofit.APIClient
import com.laboontech.webbrowser.utils.Singleton

import okhttp3.ResponseBody

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    var tvNoRecord: TextView? = null
    var rvHome: RecyclerView? = null
    var list : ArrayList<HashMap<String, String>> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        categoryData()
    }

    private fun categoryData() {
        val kProgressHUD = Singleton().createLoading(context)

//        addEmpParams["ProfilePic"]=""

        val call: Call<ResponseBody> = APIClient.getClient.getHomeData()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                kProgressHUD?.dismiss()
                val res = response.body()?.string()
                val jsondata = JSONObject(res)
                if (jsondata.getInt("code") == 200) {
                    val jsonArray = jsondata.getJSONArray("result")
                    Log.e("CategoryData", "$jsonArray")


                    //   Log.d("length", "" + upcomingJSONArray!!.length())
                    if (jsonArray.length() > 0) {
                        tvNoRecord?.visibility = View.GONE
                        rvHome?.setHasFixedSize(true)
                        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
                        rvHome?.layoutManager = mLayoutManager
                        rvHome?.itemAnimator = DefaultItemAnimator()




                        val upcomingMatchesAdapter = context?.let {
                            HomeAdapter(
                                    it,
                                    jsonArray,this@HomeFragment
                            )
                        }
                        rvHome?.adapter = upcomingMatchesAdapter
                    } else {
                        tvNoRecord?.visibility = View.VISIBLE
                    }


                } else {
                    tvNoRecord?.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("error", "$t")
                kProgressHUD?.dismiss()
            }
        })
    }

    private fun initViews(view: View) {

        tvNoRecord = view.findViewById(R.id.tv_no_record)
        rvHome = view.findViewById(R.id.rv_home)
    }
}
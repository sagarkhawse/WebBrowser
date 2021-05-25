package com.laboontech.webbrowser.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laboontech.webbrowser.R
import org.json.JSONArray

class HomeAdapter(var context: Context, var jsonArray: JSONArray, var fragment : Fragment)
    : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvCategoryName: TextView = v.findViewById(R.id.tv_category)
        var rvCategory: RecyclerView = v.findViewById(R.id.rv_category_item)

    }


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolder {
        val v: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)



        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val jsonObject = jsonArray.getJSONObject(position)

        holder.tvCategoryName.text = jsonObject.getString("category_Title")
        val jsonArray = jsonObject.getJSONArray("home_data")
        if (jsonArray.length() > 0) {

            holder.rvCategory.setHasFixedSize(true)
            val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context,3)
            holder.rvCategory.layoutManager = mLayoutManager
            holder.rvCategory.itemAnimator = DefaultItemAnimator()
            val subCategoryAdapter = SubCategoryAdapter(
                    context,
                    jsonArray, fragment
            )
            holder.rvCategory.adapter = subCategoryAdapter
        } else {
         //TODO
        }


    }

    override fun getItemCount(): Int {
        return jsonArray.length()
    }
}
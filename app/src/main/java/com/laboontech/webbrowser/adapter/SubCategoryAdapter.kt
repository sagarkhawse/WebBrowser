package com.laboontech.webbrowser.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.laboontech.webbrowser.AgentWebActivity
import com.laboontech.webbrowser.R
import org.json.JSONArray


class SubCategoryAdapter(var context: Context, var jsonArray: JSONArray, var fragment: Fragment)
    : RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>() {


    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvCategoryName: TextView = v.findViewById(R.id.tv_subtitle)
        var imgSubcategory: SimpleDraweeView = v.findViewById(R.id.img_subcategory)
        var mainLayout: LinearLayout = v.findViewById(R.id.main_lyt)

    }


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolder {
        val v: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_subcategory, parent, false)



        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val jsonObject = jsonArray.getJSONObject(position)

        holder.tvCategoryName.text = jsonObject.getString("h_title")
        holder.imgSubcategory.setImageURI(jsonObject.getString("h_image"))

        holder.mainLayout.setOnClickListener {
             val intent = Intent(context, AgentWebActivity::class.java)
               intent.putExtra("url",  jsonObject.getString("h_url"))
               context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return jsonArray.length()
    }
}
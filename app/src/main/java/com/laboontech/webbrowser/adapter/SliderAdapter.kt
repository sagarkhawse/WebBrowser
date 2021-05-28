package com.laboontech.webbrowser.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laboontech.webbrowser.R
import com.laboontech.webbrowser.adapter.SliderAdapter.SliderAdapterVH
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter
import org.json.JSONArray

class SliderAdapter(private val context: Context, var jsonArray: JSONArray) : SliderViewAdapter<SliderAdapterVH>() {


    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_slider, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val jsonObject = jsonArray.getJSONObject(position)
        Log.e("Image URL", jsonObject.getString("image"))
        viewHolder.imageViewBackground.setImageURI(Uri.parse(jsonObject.getString("image")))
       /* Glide.with(viewHolder.itemView)
                .load(jsonObject.getString("image"))
                .fitCenter()
                .into(viewHolder.imageViewBackground)*/
      //  viewHolder.itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT).show() })
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return jsonArray.length()
    }

   class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {

        var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_auto_image_slider)

   }



}
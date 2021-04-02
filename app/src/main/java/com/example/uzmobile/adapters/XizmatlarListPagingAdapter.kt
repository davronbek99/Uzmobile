package com.example.uzmobile.adapters

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.uzmobile.R
import com.example.uzmobile.models.Uzmobile
import com.example.uzmobile.models.Xizmatlar
import kotlinx.android.synthetic.main.item_uzmobile.view.*

class XizmatlarListPagingAdapter(
    var xizmatlarList: ArrayList<Xizmatlar>,
    var listener: OnImageItemXizmatClickListener
) : RecyclerView.Adapter<XizmatlarListPagingAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(xizmatlar: Xizmatlar) {
            itemView.tarif_name_item.text = xizmatlar.name
            itemView.desc_item.text = xizmatlar.desc
            itemView.imgname_tv_id.text = xizmatlar.name
            itemView.ulanish_tv_btn.setOnClickListener {
                val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + xizmatlar.ussd))
                val bundle = Bundle()
                ContextCompat.startActivity(itemView.context, call, bundle)
            }
            itemView.open_img_item.setOnClickListener {
                if (itemView.expandable_linear.visibility == View.GONE) {
                    itemView.expandable_linear.visibility = View.VISIBLE
                    itemView.open_img_item.setImageResource(R.drawable.close_icon)
                } else {
                    itemView.expandable_linear.visibility = View.GONE
                    itemView.open_img_item.setImageResource(R.drawable.open_icon)
                }
            }
            itemView.setOnClickListener {
                listener.onImageItemClick(xizmatlar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_uzmobile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(xizmatlarList[position])
        holder.itemView.setOnClickListener() {
            listener.onImageItemClick(xizmatlarList[position])
        }
    }

    override fun getItemCount(): Int =xizmatlarList.size

    interface OnImageItemXizmatClickListener {
        fun onImageItemClick(xizmatlar: Xizmatlar)
    }

}
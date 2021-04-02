package com.example.uzmobile.adapters

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.uzmobile.R
import com.example.uzmobile.models.Uzmobile
import kotlinx.android.synthetic.main.item_uzmobile.view.*

class InternetListPagingAdapter(
    var uzmobileList: ArrayList<Uzmobile>,
    var listener: OnImageItemClickListener
) : RecyclerView.Adapter<InternetListPagingAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.KITKAT)
        fun onBind(uzmobile: Uzmobile) {
            itemView.tarif_name_item.text = uzmobile.name
            itemView.desc_item.text = uzmobile.desc
            itemView.imgname_tv_id.text = uzmobile.imgname.toString()
            itemView.ulanish_tv_btn.setOnClickListener {
                val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + uzmobile.ussd))
                val bundle = Bundle()
                startActivity(itemView.context, call, bundle)
            }
            itemView.open_img_item.setOnClickListener {
                if (itemView.expandable_linear.visibility == View.GONE) {
//                    TransitionManager.beginDelayedTransition(itemView.card_view, AutoTransition())
                    itemView.expandable_linear.visibility = View.VISIBLE
                    itemView.open_img_item.setImageResource(R.drawable.close_icon)
                } else {
//                    TransitionManager.beginDelayedTransition(itemView.card_view, AutoTransition())
                    itemView.expandable_linear.visibility = View.GONE
                    itemView.open_img_item.setImageResource(R.drawable.open_icon)
                }
            }
            itemView.setOnClickListener {
                listener.onImageItemClick(uzmobile)
            }
        }

    }

//    class MyDiffUtil() : DiffUtil.ItemCallback<Uzmobile>() {
//        override fun areItemsTheSame(oldItem: Uzmobile, newItem: Uzmobile): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: Uzmobile, newItem: Uzmobile): Boolean {
//            return oldItem.equals(newItem)
//        }
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_uzmobile, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(uzmobileList[position])
        holder.itemView.setOnClickListener() {
            listener.onImageItemClick(uzmobileList[position])
        }
    }

    interface OnImageItemClickListener {
        fun onImageItemClick(uzmobile: Uzmobile)
    }

    override fun getItemCount(): Int = uzmobileList.size
}
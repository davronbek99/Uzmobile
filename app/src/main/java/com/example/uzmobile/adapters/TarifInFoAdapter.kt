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
import com.example.uzmobile.models.UzmobileTarif
import com.github.florent37.expansionpanel.ExpansionLayout
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection
import kotlinx.android.synthetic.main.item_tarif_info.view.*
import kotlinx.android.synthetic.main.item_uzmobile.view.*

class TarifInFoAdapter(
    var uzmobileTarifList: ArrayList<UzmobileTarif>,
    var listener: OnImageItemClickListenerTarif
) :
    RecyclerView.Adapter<TarifInFoAdapter.VH>() {

    private val expansionLayoutCollection = ExpansionLayoutCollection()

    init {
        expansionLayoutCollection.openOnlyOne(true)
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(uzmobileTarif: UzmobileTarif) {
            itemView.item_tarif_name.text = uzmobileTarif.nameT
            itemView.item_tarif_desc.text = uzmobileTarif.desc
            itemView.setOnClickListener {
                listener.onImageItemClickTarif(uzmobileTarif)
            }
        }

        fun expansionLayout(): ExpansionLayout {
            return itemView.expansion_layout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tarif_info, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(uzmobileTarifList[position])
        holder.itemView.setOnClickListener() {
            listener.onImageItemClickTarif(uzmobileTarifList[position])
        }
        expansionLayoutCollection.add(holder.expansionLayout())
    }

    override fun getItemCount(): Int = uzmobileTarifList.size

    interface OnImageItemClickListenerTarif {
        fun onImageItemClickTarif(uzmobileTarif: UzmobileTarif)
    }

}
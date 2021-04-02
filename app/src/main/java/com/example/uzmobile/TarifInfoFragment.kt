package com.example.uzmobile

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.uzmobile.models.UzmobileTarif
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_banner.view.*
import kotlinx.android.synthetic.main.fragment_tarif_info.view.*
import kotlinx.android.synthetic.main.item_batafsil_dialog.view.*
import kotlinx.android.synthetic.main.item_tarif_info.view.*
import kotlinx.android.synthetic.main.item_uzmobile.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TarifInfoFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var uzmobileTarif: UzmobileTarif
    private lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_tarif_info, container, false)

        uzmobileTarif = arguments?.getSerializable("uzmobileTarif") as UzmobileTarif

        root.ti_name.text = uzmobileTarif.nameT
        root.ti_desc.text = uzmobileTarif.desc
        Picasso.get().load(uzmobileTarif.imgUrl).into(root.ti_img)

        root.ti_batafsil.setOnClickListener {
            val alertDialog = AlertDialog.Builder(root.context).create()
            val view = inflater.inflate(R.layout.item_batafsil_dialog, null, false)
            alertDialog.setView(view)
            view.desc_dialog.text = uzmobileTarif.batafsil
            view.share_dialog.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, "Hey Check out this Great app:")
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Share To:"))
            }
            view.ok_id_dialog.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.window?.setGravity(Gravity.CENTER)
            alertDialog.show()
        }

        root.titozgartir.setOnClickListener {
            val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + uzmobileTarif.ussdT))
            val bundle = Bundle()
            ContextCompat.startActivity(root.context, call, bundle)
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TarifInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.uzmobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uzmobile.adapters.InternetListPagingAdapter
import com.example.uzmobile.models.Uzmobile
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_internet_view_pager.view.*
import kotlinx.android.synthetic.main.fragment_operator.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OperatorFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var root: View
    private lateinit var list: ArrayList<Uzmobile>
    private lateinit var internetListPagingAdapter: InternetListPagingAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_operator, container, false)

//        val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel: +998977172499"))
//        startActivity(call)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("all/operator/")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = ArrayList()
                for (child in snapshot.children) {
                    val value = child.getValue(Uzmobile::class.java)
                    list.add(value!!)
                }
                internetListPagingAdapter =
                    InternetListPagingAdapter(list,
                        object : InternetListPagingAdapter.OnImageItemClickListener {
                            override fun onImageItemClick(uzmobile: Uzmobile) {

                            }
                        })
                root.rv_operator.adapter = internetListPagingAdapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OperatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
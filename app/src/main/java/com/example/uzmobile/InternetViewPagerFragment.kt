package com.example.uzmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uzmobile.adapters.InternetListPagingAdapter
import com.example.uzmobile.models.Uzmobile
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_internet_view_pager.view.*

private const val ARG_PARAM1 = "param1"

class InternetViewPagerFragment : Fragment() {

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    lateinit var root: View
    private lateinit var list: ArrayList<Uzmobile>
    private lateinit var internetListPagingAdapter: InternetListPagingAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_internet_view_pager, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("all/ipn/" + param1)
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
                root.rv_internet.adapter = internetListPagingAdapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            InternetViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
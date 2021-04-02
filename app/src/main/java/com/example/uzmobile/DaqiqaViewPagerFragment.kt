package com.example.uzmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uzmobile.adapters.XizmatlarListPagingAdapter
import com.example.uzmobile.models.Xizmatlar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_daqiqa_view_pager.view.*

private const val ARG_PARAM1 = "param1"

class DaqiqaViewPagerFragmentFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    lateinit var root: View
    private lateinit var list: ArrayList<Xizmatlar>
    private lateinit var xizmatlarListPagingAdapter: XizmatlarListPagingAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_daqiqa_view_pager, container, false)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("all/daqiqa/" + param1)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = ArrayList()
                for (child in snapshot.children) {
                    val value = child.getValue(Xizmatlar::class.java)
                    list.add(value!!)
                }
                xizmatlarListPagingAdapter =
                    XizmatlarListPagingAdapter(list,
                        object : XizmatlarListPagingAdapter.OnImageItemXizmatClickListener {
                            override fun onImageItemClick(xizmatlar: Xizmatlar) {

                            }

                        })
                root.rv_daqiqa.adapter = xizmatlarListPagingAdapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            DaqiqaViewPagerFragmentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
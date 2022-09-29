package uz.invinsible.layouts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import uz.invinsible.layouts.R

class MyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root  = inflater.inflate(R.layout.fragment_layout, container, false)
        val bundle = arguments
        val text = bundle?.getString("key")
        root.findViewById<TextView>(R.id.fragment_text_id).text = text
        return root
    }
}
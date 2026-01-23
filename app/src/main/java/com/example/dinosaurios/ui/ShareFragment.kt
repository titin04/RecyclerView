package com.example.dinosaurios.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dinosaurios.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragment de ejemplo (Share).
 * Esta clase fue generada por plantilla y contiene un factory method `newInstance`.
 * Mantener los comentarios breves: la UI se infla en onCreateView.
 */
class ShareFragment : Fragment() {
    // Parámetros opcionales que pueden ser pasados vía Bundle
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout asociado a este fragment
        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    companion object {
        /**
         * Factory method de conveniencia para crear instancias del fragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShareFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
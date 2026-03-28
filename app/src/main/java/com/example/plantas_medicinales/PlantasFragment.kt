package com.example.plantas_medicinales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class PlantasFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_plantas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navegarADetalle = { key: String ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, PlantaDetalleFragment.newInstance(key))
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<LinearLayout>(R.id.cardAlbahaca).setOnClickListener { navegarADetalle("albahaca") }
        view.findViewById<LinearLayout>(R.id.cardManzanilla).setOnClickListener { navegarADetalle("manzanilla") }
        view.findViewById<LinearLayout>(R.id.cardRuda).setOnClickListener { navegarADetalle("ruda") }
        view.findViewById<LinearLayout>(R.id.cardEpazote).setOnClickListener { navegarADetalle("epazote") }
    }
}
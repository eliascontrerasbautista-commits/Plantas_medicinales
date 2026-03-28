package com.example.plantas_medicinales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class RemediosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_remedios, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navegarARemedio = { key: String ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, RemedioDetalleFragment.newInstance(key))
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<LinearLayout>(R.id.cardEstomago).setOnClickListener { navegarARemedio("estomago") }
        view.findViewById<LinearLayout>(R.id.cardInsomnio).setOnClickListener { navegarARemedio("insomnio") }
        view.findViewById<LinearLayout>(R.id.cardTos).setOnClickListener { navegarARemedio("tos") }
        view.findViewById<LinearLayout>(R.id.cardNervios).setOnClickListener { navegarARemedio("nervios") }
        view.findViewById<LinearLayout>(R.id.cardPresion).setOnClickListener { navegarARemedio("presion") }
        view.findViewById<LinearLayout>(R.id.cardCabeza).setOnClickListener { navegarARemedio("cabeza") }
    }
}
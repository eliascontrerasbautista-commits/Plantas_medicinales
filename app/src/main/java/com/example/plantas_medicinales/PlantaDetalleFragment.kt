package com.example.plantas_medicinales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

data class PlantaData(
    val nombre: String,
    val cientifico: String,
    val emoji: String,
    val chips: List<String>,
    val uso: String,
    val dosis: String,
    val contra: String,
    val interacciones: String,
    val receta: List<String>,
    val fuente: String
)

val PLANTAS_DB = mapOf(
    "albahaca" to PlantaData(
        nombre = "Albahaca", cientifico = "Ocimum basilicum", emoji = "🌿",
        chips = listOf("Dolor de oído", "Digestión", "Antibacterial", "Antinflamatorio"),
        uso = "La albahaca es muy valorada en la medicina tradicional de Tabasco. Sus hojas tienen propiedades antibacterianas y antiinflamatorias. Se usa para aliviar el dolor de oído, problemas digestivos y como antiespasmódico.",
        dosis = "Para dolor de oído: machacar 2-3 hojas frescas y extraer el jugo. Aplicar 2-3 gotas tibias. Para té digestivo: 5 hojas en 1 taza de agua caliente, 5 minutos.",
        contra = "Evitar en embarazo en grandes cantidades. No aplicar en heridas abiertas. Consultar médico si hay coagulación lenta.",
        interacciones = "Anticoagulantes (puede potenciar el efecto con uso intensivo). No hay interacciones significativas con uso normal.",
        receta = listOf("🌿 Lava bien 5 hojas frescas de albahaca", "☕ Colócalas en 1 taza de agua recién hervida", "⏱️ Deja reposar 5 minutos tapado", "🍋 Cuela, agrega unas gotas de limón y bebe después de comer"),
        fuente = "📚 Fuente: Etnobotánica de Tabasco · CICY · IMSS"
    ),
    "manzanilla" to PlantaData(
        nombre = "Manzanilla", cientifico = "Matricaria chamomilla", emoji = "🌼",
        chips = listOf("Digestión", "Colitis", "Calma", "Insomnio"),
        uso = "La manzanilla es una de las plantas más usadas en Tabasco. Es excelente para calmar el estómago, reducir la inflamación intestinal, aliviar cólicos y como relajante suave.",
        dosis = "Té: 2 cucharadas de flores secas en 1 taza de agua caliente. Reposar 8 minutos. Tomar hasta 3 veces al día, especialmente después de las comidas.",
        contra = "Personas alérgicas a plantas de la familia Asteraceae. Consultar médico durante el embarazo.",
        interacciones = "Anticoagulantes como Warfarina. Sedantes. Medicamentos para la diabetes.",
        receta = listOf("💧 Calienta 1 taza de agua hasta 90°C", "🌼 Agrega 2 cucharadas de flores de manzanilla", "⏱️ Tapa y deja reposar 8 minutos. No hiervas la flor", "🍯 Cuela, agrega miel natural y bebe tibio"),
        fuente = "📚 Fuente: Plantas Medicinales de Tabasco · UNAM · OMS"
    ),
    "ruda" to PlantaData(
        nombre = "Ruda", cientifico = "Ruta graveolens", emoji = "🌱",
        chips = listOf("Tradición", "Protección", "Dolores", "Menstrual"),
        uso = "La ruda es de gran importancia en la tradición medicinal de Tabasco. Se usa para aliviar dolores menstruales, calambres musculares y como parte de rituales de protección tradicionales.",
        dosis = "Té muy suave: 2-3 hojitas en 1 taza de agua caliente, solo 5 minutos. NO sobrepasar esta dosis.",
        contra = "NUNCA usar en embarazo. No usar en niños. Es tóxica en dosis altas.",
        interacciones = "Anticoagulantes, medicamentos para la presión. Siempre consultar médico antes de usar.",
        receta = listOf("⚠️ Use solo 2-3 hojitas pequeñas, no más", "💧 Hierva 1 taza de agua y retire del fuego", "🌱 Agregue las hojitas y tape 5 minutos", "🍵 Cuele bien y beba una sola taza"),
        fuente = "📚 Fuente: Flora Medicinal de México · UNAM · SSA"
    ),
    "epazote" to PlantaData(
        nombre = "Epazote", cientifico = "Dysphania ambrosioides", emoji = "🪴",
        chips = listOf("Antiparasitario", "Digestión", "Flatulencia", "Tradicional"),
        uso = "El epazote es nativo de México y muy común en Tabasco. Usado como antiparasitario natural, para aliviar la flatulencia y mejorar la digestión.",
        dosis = "Té: 1 rama pequeña en 1 taza de agua, hervir 5 minutos. Tomar en ayunas por 3 días.",
        contra = "No usar en embarazo. Evitar en niños menores de 5 años. No usar el aceite esencial puro — es tóxico.",
        interacciones = "No hay interacciones significativas en uso normal. En uso intensivo consultar médico.",
        receta = listOf("🪴 Lava una rama pequeña de epazote fresco", "🫕 Ponla a hervir en 1 taza de agua por 5 minutos", "🧊 Retira del fuego y deja enfriar", "⏰ Toma en ayunas. Repite 3 días seguidos"),
        fuente = "📚 Fuente: Plantas Medicinales de Tabasco · ECOSUR · IMSS"
    )
)

class PlantaDetalleFragment : Fragment() {

    companion object {
        fun newInstance(plantaKey: String): PlantaDetalleFragment {
            val fragment = PlantaDetalleFragment()
            val args = Bundle()
            args.putString("planta_key", plantaKey)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_planta_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val key = arguments?.getString("planta_key") ?: return
        val planta = PLANTAS_DB[key] ?: return

        view.findViewById<TextView>(R.id.tvDetalleName).text = planta.nombre
        view.findViewById<TextView>(R.id.tvDetalleSci).text = planta.cientifico
        view.findViewById<TextView>(R.id.tvDetalleEmoji).text = planta.emoji
        view.findViewById<TextView>(R.id.tvDetalleUso).text = planta.uso
        view.findViewById<TextView>(R.id.tvDetalleDosis).text = planta.dosis
        view.findViewById<TextView>(R.id.tvDetalleContra).text = planta.contra
        view.findViewById<TextView>(R.id.tvDetalleInter).text = planta.interacciones
        view.findViewById<TextView>(R.id.tvDetalleFuente).text = planta.fuente

        // Chips
        val chipsLayout = view.findViewById<LinearLayout>(R.id.layoutChips)
        chipsLayout.removeAllViews()
        planta.chips.forEach { chip ->
            val tv = TextView(requireContext())
            tv.text = chip
            tv.setTextColor(android.graphics.Color.WHITE)
            tv.textSize = 12f
            tv.setPadding(24, 10, 24, 10)
            val bg = android.graphics.drawable.GradientDrawable()
            bg.setColor(android.graphics.Color.parseColor("#2D6A4F"))
            bg.cornerRadius = 40f
            tv.background = bg
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 16, 0)
            tv.layoutParams = params
            chipsLayout.addView(tv)
        }

        // Receta
        val recetaLayout = view.findViewById<LinearLayout>(R.id.layoutReceta)
        recetaLayout.removeAllViews()
        planta.receta.forEachIndexed { index, paso ->
            val stepView = layoutInflater.inflate(R.layout.item_paso_receta, recetaLayout, false)
            stepView.findViewById<TextView>(R.id.tvPasoNum).text = "${index + 1}"
            stepView.findViewById<TextView>(R.id.tvPasoTexto).text = paso
            recetaLayout.addView(stepView)
        }

        // Botón regresar
        view.findViewById<TextView>(R.id.btnRegresar).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}
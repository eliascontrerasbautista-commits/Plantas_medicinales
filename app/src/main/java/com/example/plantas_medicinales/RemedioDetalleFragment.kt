package com.example.plantas_medicinales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

data class RemedioData(
    val icon: String,
    val titulo: String,
    val desc: String,
    val plantasKeys: List<String>,
    val recetaTitulo: String,
    val pasos: List<String>,
    val advertencia: String
)

val REMEDIOS_DB = mapOf(
    "estomago" to RemedioData(
        icon = "🏥", titulo = "Dolor de Estómago", desc = "Inflamación, cólicos o malestar digestivo",
        plantasKeys = listOf("manzanilla", "epazote"),
        recetaTitulo = "Té de Estafiate y Manzanilla",
        pasos = listOf("🌼 Hierve 1 taza de agua", "🌿 Agrega 1 cdita de manzanilla y media de estafiate", "⏱️ Reposa 10 minutos tapado", "🍯 Cuela, endulza con miel y bebe tibio"),
        advertencia = "⚠️ Si el dolor es muy intenso, dura más de 2 días o tiene fiebre, consulte a su médico."
    ),
    "insomnio" to RemedioData(
        icon = "🌙", titulo = "Insomnio", desc = "Dificultad para conciliar o mantener el sueño",
        plantasKeys = listOf("manzanilla", "albahaca"),
        recetaTitulo = "Té Relajante para Dormir",
        pasos = listOf("🫖 Hierve 1 taza de agua y retírala del fuego", "🌼 Agrega 1 cdita de manzanilla", "⏱️ Deja reposar 10 minutos tapado", "🌙 Cuela y toma 30 minutos antes de acostarse"),
        advertencia = "⚠️ No mezclar con alcohol ni medicamentos sedantes. Si persiste más de 2 semanas, consulte a su médico."
    ),
    "tos" to RemedioData(
        icon = "🤧", titulo = "Tos y Resfriado", desc = "Tos seca o con flema, congestionamiento",
        plantasKeys = listOf("albahaca", "epazote"),
        recetaTitulo = "Jarabe de Guate con Miel",
        pasos = listOf("🌿 Lava y pica unas ramas de guate o albahaca", "💧 Hierve en 2 tazas de agua por 10 minutos", "🍋 Cuela y agrega jugo de medio limón", "🍯 Añade 2 cucharadas de miel. Toma tibio"),
        advertencia = "⚠️ Si la tos dura más de 7 días o hay dificultad para respirar, acuda al médico."
    ),
    "nervios" to RemedioData(
        icon = "🧠", titulo = "Nervios y Estrés", desc = "Tensión, ansiedad o nerviosismo",
        plantasKeys = listOf("manzanilla", "ruda"),
        recetaTitulo = "Infusión de Naranjo Agrio",
        pasos = listOf("🍊 Recoge 4-5 hojas frescas de naranjo agrio", "💧 Hierve 1 taza de agua y coloca las hojas", "⏱️ Deja reposar 8 minutos tapado", "😌 Cuela, agrega miel y toma en un lugar tranquilo"),
        advertencia = "⚠️ Si la ansiedad es severa o afecta su vida diaria, consulte con un profesional de salud mental."
    ),
    "presion" to RemedioData(
        icon = "❤️", titulo = "Presión Alta", desc = "Hipertensión arterial, presión elevada",
        plantasKeys = listOf("epazote", "manzanilla"),
        recetaTitulo = "Agua de Chaya Natural",
        pasos = listOf("🌿 Lava 6 hojas de chaya fresca", "🫕 Hierve en 1 litro de agua por 15 minutos", "🧊 Deja enfriar y agrega hielo y limón al gusto", "📅 Toma 1-2 vasos al día, no sustituye el medicamento"),
        advertencia = "⚠️ IMPORTANTE: La presión alta requiere atención médica. Esta receta es complementaria, NO sustituye el tratamiento médico."
    ),
    "cabeza" to RemedioData(
        icon = "😣", titulo = "Dolor de Cabeza", desc = "Cefalea, migraña o tensión craneal",
        plantasKeys = listOf("ruda", "albahaca"),
        recetaTitulo = "Cataplasma de Ruda para la Sien",
        pasos = listOf("🌱 Toma 3-4 hojitas frescas de ruda (no más)", "👐 Aplasta suavemente las hojas", "🤲 Coloca las hojas sobre las sienes y frente", "⏱️ Deja actuar 15-20 minutos"),
        advertencia = "⚠️ Si el dolor es muy intenso o va acompañado de fiebre o visión borrosa, acuda a urgencias."
    )
)

class RemedioDetalleFragment : Fragment() {

    companion object {
        fun newInstance(remedioKey: String): RemedioDetalleFragment {
            val fragment = RemedioDetalleFragment()
            val args = Bundle()
            args.putString("remedio_key", remedioKey)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_remedio_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.btnVolverAtras).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val key = arguments?.getString("remedio_key") ?: return
        val remedio = REMEDIOS_DB[key] ?: return

        view.findViewById<TextView>(R.id.tvRemedioIcon).text = remedio.icon
        view.findViewById<TextView>(R.id.tvRemedioTitulo).text = remedio.titulo
        view.findViewById<TextView>(R.id.tvRemedioDesc).text = remedio.desc
        view.findViewById<TextView>(R.id.tvRecetaTitulo).text = remedio.recetaTitulo
        view.findViewById<TextView>(R.id.tvAdvertencia).text = remedio.advertencia

        // Plantas recomendadas
        val plantasLayout = view.findViewById<LinearLayout>(R.id.layoutPlantasRemedio)
        plantasLayout.removeAllViews()
        remedio.plantasKeys.forEach { pk ->
            val planta = PLANTAS_DB[pk] ?: return@forEach
            val itemView = layoutInflater.inflate(R.layout.item_planta_remedio, plantasLayout, false)
            itemView.findViewById<TextView>(R.id.tvPlantaRemedioEmoji).text = planta.emoji
            itemView.findViewById<TextView>(R.id.tvPlantaRemedioNombre).text = planta.nombre
            itemView.findViewById<TextView>(R.id.tvPlantaRemedioSci).text = planta.cientifico
            itemView.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, PlantaDetalleFragment.newInstance(pk))
                    .addToBackStack(null)
                    .commit()
            }
            plantasLayout.addView(itemView)
        }

        // Pasos de receta
        val pasosLayout = view.findViewById<LinearLayout>(R.id.layoutPasosRemedio)
        pasosLayout.removeAllViews()
        remedio.pasos.forEachIndexed { index, paso ->
            val stepView = layoutInflater.inflate(R.layout.item_paso_receta, pasosLayout, false)
            stepView.findViewById<TextView>(R.id.tvPasoNum).text = "${index + 1}"
            stepView.findViewById<TextView>(R.id.tvPasoTexto).text = paso
            pasosLayout.addView(stepView)
        }

        view.findViewById<TextView>(R.id.btnRegresarRemedio).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}
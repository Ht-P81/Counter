package com.example.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Inicializamos la variable, pues en el build.gradle hemos habilitado buildFeatures{
    //        viewBinding true
    //    }
    // ahora en el MainActivity creamos la variable binding
    private lateinit var binding: ActivityMainBinding
    // variable que contará
    private var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicilizamamos la variable y por ultimo binding.root
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creamos el metodo contador por si lo tenemos que implementar en más partes del codigo
        setContador()

        // Mapeamos el boton y creamos el evento que incremente en uno cada vez que lo pulsemos
        binding.btnSuma.setOnClickListener {
            count++
            setContador()
        }

        // On click long, si dejamos pulsado el botón resetea el contador
        binding.btnSuma.setOnLongClickListener {
            count = 0
            setContador()
            true
        }

        // Mapeamos el boton y creamos el evento que incremente en uno cada vez que lo pulsemos
        binding.btnResta.setOnClickListener {
            count--
            setContador()
        }

        // On click long, si dejamos pulsado el botón resetea el contador
        binding.btnResta.setOnLongClickListener {
            count = 0
            setContador()
            true
        }
        //para imprimir información acerca del onCreate
        Log.i("Titulo Ciclo de vida", "Primer Ciclo onCreate")
    } // LLAVE DE CIERRA DEL ONCREATE

    // 6 METODO ONRESTART, lo ponemos aquí pq va continuo al onStart pero el orden de ejecución es 6
    override fun onRestart() {
        super.onRestart()
        Log.i("Titulo Ciclo de vida", "Sexto Ciclo onRestart")
    }

    // 2 METODO ONSTART
    override fun onStart() {
        super.onStart()
        //para imprimir información acerca del onStart
        // Log.i para obtener información
        Log.i("Titulo Ciclo de vida", "Segundo Ciclo onStart")
    }

    // 3 METODO ONRESUME
    override fun onResume() {
        super.onResume()
        Log.i("Titulo Ciclo de vida", "Tercer Ciclo onResume")
    }

    // 4 METODO ONPAUSE
    // cuando ejecutemos la aplicacion no aparecerá en consola este log.i,
    // a menos que en el emulador salgamos de la app, entonces aparecerá en el logcat el mensaje log.i
    override fun onPause() {
        super.onPause()
        Log.i("Titulo Ciclo de vida", "Cuarto Ciclo onPause")
    }

    // 5 METODO ONSTOP
    // este metodo se ejecuta cuando nuestra app no sea visible (conjunto con onPause, son continuous)
    override fun onStop() {
        super.onStop()
        Log.i("Titulo Ciclo de vida", "Quinto Ciclo onStop")
    }

    // 7 METODO ONDESTROY
    override fun onDestroy() {
        super.onDestroy()
        Log.i("Titulo Ciclo de vida", "Septimo y ultimo, Ciclo onDestroy")
        Toast.makeText(this, "El ciclo de vida de la app ha finalizado", Toast.LENGTH_SHORT).show()
    }

    // Metodo que cuenta
    private fun setContador() {
        //Mapeamos la variable contador con el tvContador vía binding no via R.id...
        binding.tvContador.text = count.toString()
    }

    // Metodo para guardar valores
    override fun onSaveInstanceState(outState: Bundle) {
        //Le decimos qué es lo que queremos guardar
        outState.run{
            //guardamos el valor de la variable
            putInt(PARAMETRO_CONTAR, count)
        }
        super.onSaveInstanceState(outState)
    }

    // Actulizar respetando los valores
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // GUardar el estado de la instancia obteniendo su valor por el key
        count = savedInstanceState.getInt(PARAMETRO_CONTAR)
        super.onRestoreInstanceState(savedInstanceState)
        setContador()
    }

    companion object {
        // Creamos una variable que guarde la contrasenña (key) del bundle
        // esto se hace como una buena práctica para no equivocarnos con el string que se pasa por parámetro
        private const val PARAMETRO_CONTAR:String ="Parametro_contador"
    }
}

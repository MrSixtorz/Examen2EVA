package com.example.examen2eva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examen2eva.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = DatabaseHelper(this)
        var nom = intent.getStringExtra("nombre")
        var valor = intent.getStringExtra("valor")
        var empresa = intent.getStringExtra("empresa")
        var year = intent.getStringExtra("year")

        binding.textN.text = nom
        binding.textV.text = valor
        binding.textE.text = empresa
        binding.textY.text = year

        binding.buttonA.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java).apply {
                putExtra("nombre",nom)
                putExtra("valor",valor)
            }
            startActivity(intent)
        }
        binding.buttonG.setOnClickListener {
            var vj = Videojuego(nom!!, valor!!.toFloat(),empresa!!,year!!.toInt())
            db.escribir(vj)
            Toast.makeText(this,"${vj.toString()} guardado en Base de Datos", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, SaveActivity::class.java))
        }
        binding.buttonN.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}
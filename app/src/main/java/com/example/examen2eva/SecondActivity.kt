package com.example.examen2eva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examen2eva.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nom = intent.getStringExtra("nombre")
        var valor = intent.getStringExtra("valor")

        binding.textNombre.text = nom
        binding.textValor.text = valor

        binding.buttonAtras.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        binding.buttonSig.setOnClickListener {
            var empresa = binding.editTextEmpresa.text.toString()
            var year = binding.editTextYear.text.toString()
            var yearInt = year.toIntOrNull()
            if (empresa.isNullOrEmpty()){
                Toast.makeText(this, "Empresa no puede estar vacío", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (yearInt == null || yearInt<1970 || yearInt>2024){
                Toast.makeText(this, "Año superior a 1970 y numérico e inferior a 2024", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(this,ThirdActivity::class.java).apply {
                putExtra("nombre", nom)
                putExtra("valor", valor)
                putExtra("empresa", empresa)
                putExtra("year", year)
            }
            startActivity(intent)
        }
    }
}



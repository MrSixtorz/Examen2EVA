package com.example.examen2eva

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examen2eva.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Siguiente.setOnClickListener{
            var nom = binding.editTextNombre.text.toString()
            var valor = binding.editTextValor.text.toString()
            var valorFloat = valor.toFloatOrNull()
            if (nom.isNullOrEmpty()){
                Toast.makeText(this, "Nombre del Videjuego no puede estar vacío", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (valorFloat==null || valorFloat<0 || valorFloat>10){
                Toast.makeText(this, "Valoración mayor que 0 y puede ser decimal", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(this,SecondActivity::class.java).apply {
                putExtra("nombre", nom)
                putExtra("valor", valor)
            }
            startActivity(intent)
        }
    }
}
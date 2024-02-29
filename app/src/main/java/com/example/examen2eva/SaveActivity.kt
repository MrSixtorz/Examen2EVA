package com.example.examen2eva

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen2eva.databinding.ActivitySaveBinding

class SaveActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySaveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Creamos la instancia de la clase DatabaseHelper, nos va a permitir acceder a los recursos de la aplicación
        val db = DatabaseHelper(this)

        val listado = db.lectura().joinToString("\n")

        binding.textLista.text = listado

        binding.buttonNVJ.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}

// Clase DatabaseHelper
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "Videojuegos.db"
        private const val TABLA_VIDEOJUEGOS = "JuegosComprados"
        private const val KEY_ID = "VJ_ID"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_VALORACION = "valoracion"
        private const val COLUMN_EMPRESA = "empresa"
        private const val COLUMN_YEAR = "año_lanzamiento"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLA_VIDEOJUEGOS (" +
                "$KEY_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NOMBRE STRING," +
                "$COLUMN_VALORACION FLOAT," +
                "$COLUMN_EMPRESA STRING," +
                "$COLUMN_YEAR INTEGER)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_VIDEOJUEGOS")
        onCreate(db)
    }

    fun escribir(videojuego : Videojuego):Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, videojuego.getNombre())
            put(COLUMN_VALORACION, videojuego.getValor())
            put(COLUMN_EMPRESA, videojuego.getEmpresa())
            put(COLUMN_YEAR, videojuego.getYear())
        }
        val id= db.insert(TABLA_VIDEOJUEGOS, null, values)
        db.close()
        return id
    }


    @SuppressLint("Range")
    fun lectura(): ArrayList<Videojuego> {
        val lectura = ArrayList<Videojuego>()
        val selectQuery = "SELECT * FROM $TABLA_VIDEOJUEGOS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val valor = cursor.getFloat(cursor.getColumnIndex(COLUMN_VALORACION))
                val empresa = cursor.getString(cursor.getColumnIndex(COLUMN_EMPRESA))
                val year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR))
                lectura.add(Videojuego(nombre, valor, empresa, year))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lectura
    }

}
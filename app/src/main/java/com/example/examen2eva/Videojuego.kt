package com.example.examen2eva

import android.os.Parcel
import android.os.Parcelable

class Videojuego(
    private var nombre: String,
    private var valor: Float,
    private var empresa: String,
    private var year: Int
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre);
        parcel.writeFloat(valor);
        parcel.writeString(empresa);
        parcel.writeInt(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Videojuego> {
        override fun createFromParcel(parcel: Parcel): Videojuego {
            return Videojuego(parcel)
        }

        override fun newArray(size: Int): Array<Videojuego?> {
            return arrayOfNulls(size)
        }
    }
    fun getNombre(): String {
        return nombre
    }
    fun getValor():Float = valor
    fun getEmpresa():String = empresa
    fun getYear():Int = year
    override fun toString(): String {
        return "Videojuego(Nombre=$nombre, Valoración=$valor, Empresa=$empresa, Año=$year)"
    }
}
package com.example.p004kotlin

class ReciboNomina(private val numRecibo: Int, private var nombre: String, private var horasTrabNormal: Int, private var horasTrabExtras: Int, private var puesto: Int) {

    fun calcularSubtotal(): Double {
        val pagoBase = 200.0
        val pagoPorHora: Double = when (puesto) {
            1 -> pagoBase * 1.2 // 20% de incremento
            2 -> pagoBase * 1.5 // 50% de incremento
            3 -> pagoBase * 2.0 // 100% de incremento
            else -> pagoBase
        }
        val subtotal = (horasTrabNormal * pagoPorHora) + (horasTrabExtras * pagoPorHora * 2)
        return subtotal
    }

    fun calcularImpuesto(): Double {
        val subtotal = calcularSubtotal()
        val impuesto = subtotal * 0.16 // 16% del subtotal
        return impuesto
    }

    fun calcularTotal(): Double {
        val subtotal = calcularSubtotal()
        val impuesto = calcularImpuesto()
        val total = subtotal - impuesto
        return total
    }

    // Métodos getter y setter para los atributos

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getHorasTrabNormal(): Int {
        return horasTrabNormal
    }

    fun setHorasTrabNormal(horasTrabNormal: Int) {
        this.horasTrabNormal = horasTrabNormal
    }

    fun getHorasTrabExtras(): Int {
        return horasTrabExtras
    }

    fun setHorasTrabExtras(horasTrabExtras: Int) {
        this.horasTrabExtras = horasTrabExtras
    }

    fun getPuesto(): Int {
        return puesto
    }

    fun setPuesto(puesto: Int) {
        this.puesto = puesto
    }
}

package main.heroe

import main.inventario.Inventario
import main.trabajo.Trabajo


class Heroe(var hp:Int, var fuerza:Int, var velocidad:Int, var inteligencia:Int) {
  require(hp>1, "El hp debe ser mayor a 1")
  require(fuerza>1, "La fuerza debe ser mayor a 1")
  require(velocidad>1, "La velocidad debe ser mayor a 1")
  require(inteligencia>1, "La inteligencia debe ser mayor a 1")
  var trabajo:Trabajo = null
  var inventario:Inventario = null
}
package main.heroe

import main.inventario.Inventario
import main.Trabajo.Trabajo


class Heroe(var hp:Int, var fuerza:Int, var velocidad:Int, var inteligencia:Int) {
  var trabajo:Trabajo = null
  var inventario:Inventario = null
}
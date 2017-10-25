package main.heroe

import main.inventario.Inventario
import main.trabajo.Trabajo


class Heroe(var hp:Int, var fuerza:Int, var velocidad:Int, var inteligencia:Int) {
  //TODO: Si los stats son menores que 1, tienen que ser 1

  var trabajo:Trabajo = null
  var inventario:Inventario = null
}
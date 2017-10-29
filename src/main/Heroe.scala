package main

import main.trabajo.Trabajo

case class Heroe(var hpBase:Int, var fuerzaBase:Int, var velocidadBase:Int, var inteligenciaBase:Int, var trabajo : Option[Trabajo],
   var inventario : List[Item]) {
  require(hpBase>0, "El hp debe ser positivo")
  require(fuerzaBase>0, "La fuerza debe ser positiva")
  require(velocidadBase>0, "La velocidad debe ser positiva")
  require(inteligenciaBase>0, "La inteligencia debe ser positiva")
   
}

package main

import main.trabajo.Trabajo
import main.trabajo.Ladron

case class Heroe(val hpBase: Int = 1, val fuerzaBase: Int = 1, val velocidadBase: Int = 1, val inteligenciaBase: Int = 1, val trabajo: Option[Trabajo] = None,
                 var inventario: Option[List[Item]] = None) {
  require(hpBase > 0, "El hp debe ser mayor a cero")
  require(fuerzaBase > 0, "La fuerza debe ser mayor a cero")
  require(velocidadBase > 0, "La velocidad debe ser mayor a cero")
  require(inteligenciaBase > 0, "La inteligencia debe ser mayor a cero")
  
  def cambiarTrabajo(heroe: Heroe, trabajo: Trabajo): Heroe = {
    return this.copy(trabajo = Option(trabajo))
  }
  
  def equiparHeroe : Heroe = {
    trabajo match {
      case Some(trab) => return trab.modificarStats(this)
      case None => return this
    }
  }
}

package main

import main.trabajo.Trabajo

case class Heroe(hpBase: Int = 1, fuerzaBase: Int = 1, velocidadBase: Int = 1, inteligenciaBase: Int = 1,
                          trabajo: Option[Trabajo] = None, inventario: Option[List[Item]] = None) {
  require(hpBase > 0, "El hp debe ser mayor a cero")
  require(fuerzaBase > 0, "La fuerza debe ser mayor a cero")
  require(velocidadBase > 0, "La velocidad debe ser mayor a cero")
  require(inteligenciaBase > 0, "La inteligencia debe ser mayor a cero")
  
  def cambiarTrabajo(heroe: Heroe, trabajo: Trabajo): Heroe = {
    return this.copy(trabajo = Option(trabajo))
  }
}

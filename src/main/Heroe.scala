package main

import main._

case class Heroe(hpBase: Int = 1, fuerzaBase: Int = 1, velocidadBase: Int = 1, inteligenciaBase: Int = 1,
                          trabajo: Option[Trabajo] = None, items: Option[List[Item]] = None) {
  require(hpBase > 0, "El hp debe ser mayor a cero")
  require(fuerzaBase > 0, "La fuerza debe ser mayor a cero")
  require(velocidadBase > 0, "La velocidad debe ser mayor a cero")
  require(inteligenciaBase > 0, "La inteligencia debe ser mayor a cero")
  
  val statsIniciales = new Stat(hpBase,fuerzaBase,velocidadBase,inteligenciaBase)
  val inventario = new Inventario(items, this)
  
  private def statsDeTrabajo : Stat = {
    trabajo match {
      case Some(trabajo) =>  trabajo.stats
      case None          =>  new Stat    //tiene vals por default en 0
    }
  }
  
  def cambiarTrabajo(heroe: Heroe, trabajo: Trabajo): Heroe = {
    return this.copy(trabajo = Option(trabajo))
  }
  
  def stats : Stat = {
    return inventario.mergearStatsCon(statsIniciales.mergarCon(statsDeTrabajo))
  }
}

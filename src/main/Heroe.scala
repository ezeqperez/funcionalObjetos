package main

import main._

case class Heroe(statsIniciales : Stat = new Stat, trabajo: Option[Trabajo] = None, items: Option[List[Item]] = None) {
  
  val inventario = new Inventario(items,this)
  
  def cambiarTrabajo(heroe: Heroe, trabajo: Trabajo): Heroe = {
    return this.copy(trabajo = Option(trabajo))
  }
  
  def probar(item: Item) = {
    item.efectoPara(this, stats)
  }
  
  def statPrincipal = trabajo.map(_.statPrincipal)
  
  def stats = trabajo.map(_.stats).fold(statsIniciales){statsIniciales.+ _}
  
  def fuerzaBase = statsIniciales.fuerza
  def inteligenciaBase = statsIniciales.inteligencia
}

package main

import main._

case class Heroe(val statsIniciales: Stat = Stat(), val trabajo: Option[Trabajo] = None, 
    val inventario: Inventario = new Inventario()) {
  
  def this(statsIniciales: Stat, trabajo: Option[Trabajo], items: List[Item]) {
    this(statsIniciales, trabajo, inventario = new Inventario(items))
  }
  
  def cambiarTrabajo(trabajo: Trabajo): Heroe = {
    return this.copy(trabajo = Option(trabajo))
  }

  def equipar(item: Item): Heroe = {
    this.copy(inventario = inventario.equipar(item, this))
  }
  
  def probar(item: Item) = {
    item.efectoPara(this, stats)
  }

  def statPrincipal = trabajo.map(_.statPrincipal)
 
  def stats = trabajo.fold(statsIniciales){_.apply(statsIniciales)}
  
  def fuerzaBase = statsIniciales.fuerza
  def inteligenciaBase = statsIniciales.inteligencia

  def getHp = stats.hp
  def getFuerza = stats.fuerza
  def getVelocidad = stats.velocidad
  def getInteligencia = stats.inteligencia
  
}

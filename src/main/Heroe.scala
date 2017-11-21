package main

import main._

case class Heroe(statsIniciales: Stat = Stat(), trabajo: Option[Trabajo] = None, 
    inventario: Inventario = new Inventario(), tareasRealizadas: List[Tarea] = List()) {
  
  def this(statsIniciales: Stat, trabajo: Option[Trabajo], items: List[Item], tareasRealizadas: List[Tarea]) {
    this(statsIniciales, trabajo, inventario = new Inventario(items).crearInventario(items), tareasRealizadas)
  }
  
  def cambiarTrabajo(trabajo: Trabajo): Heroe = {
    return this.copy(trabajo = Option(trabajo))
  }

  def equipar(item: Item): Heroe = {
    this.copy(inventario = inventario.equipar(item, this))
  }

  def statPrincipal = trabajo.map(_.statPrincipal)
  
  def stats = aplicarTareasA(statsItemsYTrabajo)
  
  
  private def conTrabajo = trabajo.fold(statsIniciales){_.apply(statsIniciales)}
 
  private def statsItemsYTrabajo = inventario.listoParaEquiparEn(this)(conTrabajo)
  
  private def aplicarTareasA = tareasRealizadas.map(t => t.efectoPara(this)_).reduce((x,y) => y compose x)
  
  
  def fuerzaBase = statsIniciales.fuerza
  def inteligenciaBase = statsIniciales.inteligencia

  def getHp = stats.hp
  def getFuerza = stats.fuerza
  def getVelocidad = stats.velocidad
  def getInteligencia = stats.inteligencia
  
}

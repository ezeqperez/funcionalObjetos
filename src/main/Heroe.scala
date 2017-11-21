package main

import main._

case class Heroe(statsIniciales: Stat = Stat(), trabajo: Option[Trabajo] = None, 
    items:List[Item]= List() , tareasRealizadas: List[Tarea] = List()) {
  
  val inventario: Inventario = Inventario(items,this)
  def cambiarTrabajo(trabajo: Trabajo): Heroe = {
    return this.copy(trabajo = Option(trabajo))
  }

  def equipar(item: Item): Heroe = {
   return this.copy(items = inventario.agregarItem(item))
  
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

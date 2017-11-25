package main

case class Mision(tareas: List[Tarea] = List()) {

  def recompensa: (Equipo => Equipo) = null
  
  def serRealizadaPor(equipo: Resultado) = {
    queHagaLasTareasUn(equipo).map(recompensa)
  }
  
  private def queHagaLasTareasUn(unEquipo: Resultado): Resultado = {
    return tareas.foldLeft(unEquipo){
      
      case (Exito(eq), tarea: Tarea)  => eq.hacer(tarea)
      case (estado: Resultado, _)     => estado
    }
  }
}

object pegarleAlColo extends Mision(List(pelearContraMonstruo)) {
  def recompensa(equipo: Equipo) = equipo.cobrarOro(10)
}

object robarCosas extends Mision(List(new robarTalisman(talismanDedicacion))) {
  def recompensa(equipo: Equipo) = equipo.cobrarOro(1000)
}
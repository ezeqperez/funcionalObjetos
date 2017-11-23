package main

import scala.util.Try

abstract case class Mision(tareas: List[Tarea] = List()) {

  def recompensa: (Equipo => Equipo)
  
  def serRealizadaPor(equipo: Equipo) = {
    queHagaLasTareasUn(equipo).map(recompensa)
  }
  
  private def queHagaLasTareasUn(equipo: Equipo): Resultado = {
    return tareas.foldLeft(Resultado(equipo)){
      
      case (Exito(equipo), tarea: Tarea)  => equipo.hacer(tarea)
      case (estado: Fallo, _)             => estado
    }
  }
}
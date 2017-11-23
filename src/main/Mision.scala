package main

import scala.util.Try

abstract case class Mision(tareas: List[Tarea] = List()) {

  def recompensa: (Equipo => Equipo)
  
  def serRealizadaPor(unEquipo: Equipo): Resultado = {
    return tareas.foldLeft(Resultado(unEquipo)){
      
      case (Exito(equipo), tarea: Tarea)         => equipo.hacer(tarea)
      case (estado: Fallo, _)                    => estado
    }
  }
  
}
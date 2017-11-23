package main

import scala.util.Try


sealed trait Resultado { 
  def equipo: Equipo 

  
}

object Resultado {
  def apply(equipo: => Equipo): Resultado = 
    try {
      Exito(equipo)
    } catch {
      case error: NoPuedeRealizarTarea => Fallo(equipo, error.tarea)
    }
}

case class Exito(equipo: Equipo) extends Resultado
case class Fallo(equipo: Equipo, tareaFallida: Tarea) extends Resultado



abstract case class Mision(tareas: List[Tarea] = List()) {

  def recompensa: (Equipo => Equipo)
  
  def serRealizadaPor(unEquipo: Equipo): Resultado = {
    return tareas.foldLeft(Resultado(unEquipo)){
      
      case (Exito(equipo), tarea: Tarea)         => Resultado(equipo.hacer(tarea))
      case (estado: Fallo, _)                    => estado
    }
  }
  
}
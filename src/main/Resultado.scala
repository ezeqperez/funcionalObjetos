package main

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
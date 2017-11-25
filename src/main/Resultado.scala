package main

import java.util.NoSuchElementException

sealed trait Resultado { 
  def equipo: Equipo
  
  def tareaFallida: Option[Tarea] = None
  
  def map(f: (Equipo => Equipo)) = {
    this match {
      case Exito(equipo)               => Resultado(f(equipo))
      case error @ Fallo(_,_)          => error
      case error @ FalloGeneral(_,_)   => error
    }
  }
  
  def isSuccess = {
    this match {
      case Exito(_)   => true
      case _          => false
    }
  }
  
  def get = {
    this match {
      case Exito(equipo)  => equipo
      case _              => throw new Exception("Get de Resultado Fallido")
    }
  }
  
}

object Resultado {
  def apply(equipo: => Equipo): Resultado = 
    try {
      Exito(equipo)
    } catch {
      case error: NoPuedeRealizarTarea => Fallo(equipo, error.tarea)
      case _: NoSuchElementException => FalloGeneral(equipo, throw new Exception("Get de Resultado Fallido"))
    }
}
      //constructor privado, asi no instancio directamente estas clases, y obligo a usar Resultado(Equipo)
case class Exito private(equipo: Equipo) extends Resultado
case class Fallo private(equipo: Equipo, tarea: Tarea) extends Resultado {
  override def tareaFallida = Some(tarea)
}
case class FalloGeneral private(equipo: Equipo, excepcion: Exception) extends Resultado
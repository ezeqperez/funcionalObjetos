package main

import scala.util.Try

abstract case class Mision(tareas: List[Tarea] = List()) {

  def recompensa: (Equipo => Equipo)
  
  def serRealizadaPor(unEquipo: Equipo) = {
    var posibleEquipo = tareas.foldLeft(Try(unEquipo))((s: Try[Equipo],t: Tarea) => Try(s.get.hacer(t)))
    
    if(posibleEquipo.isSuccess)
      (recompensa(posibleEquipo.get),None)
    else
      (unEquipo,Some(posibleEquipo))
  }
        //esto va con estado del ejercicio de microprocesador
  
  
  
}
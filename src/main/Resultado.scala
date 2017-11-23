package main

sealed trait Resultado { 
  def equipo: Equipo
  
  def tareaFallida: Option[Tarea] = None
}

object Resultado {
  def apply(equipo: => Equipo): Resultado = 
    try {
      Exito(equipo)
    } catch {
      case error: NoPuedeRealizarTarea => Fallo(equipo, error.tarea)
    }
}
      //constructor privado, asi no instancio directamente estas clases, y obligo a usar Resultado(Equipo)
case class Exito private(equipo: Equipo) extends Resultado
case class Fallo private(equipo: Equipo, tarea: Tarea) extends Resultado {
  override def tareaFallida = Some(tarea)
}
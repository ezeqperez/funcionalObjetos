package main

case object Taberna {
  val misiones: List[Mision] = List(pegarleAlColo)
  type Criterio = (Equipo,Equipo) => Boolean
  
  def elegirMejorMisionPara(equipo: Equipo)(criterio: Criterio) = {
    ordenarMisionesSegun(Resultado(equipo))(criterio).head
  }

  
  private def ordenarMisionesSegun(equipo: Resultado)(criterio: Criterio) = {
    misiones.sortWith((m1: Mision, m2: Mision) => criterio(m1.serRealizadaPor(equipo).get, m2.serRealizadaPor(equipo).get))
  }
        //FIXME esto es feo, hay que pensar una mejor manera
  
}

object criterioMayorOro {
  def apply(equipo: Equipo, otroEquipo: Equipo) = {
    equipo.pozo > otroEquipo.pozo
  }
}

object masHeroes {
  def apply(equipo: Equipo, otroEquipo: Equipo) = {
    equipo.integrantes.size > otroEquipo.integrantes.size
  }
}
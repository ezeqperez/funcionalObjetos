package main

case object Taberna {
  val misiones: List[Mision] = List(pegarleAlColo)
  type Criterio = (Equipo,Equipo) => Boolean
  
  def elegirMejorMisionPara(equipo: Equipo)(criterio: Criterio) = {
    var mejorEstado = Resultado(misiones.map(_.serRealizadaPor(Resultado(equipo))).filter(_.isSuccess).map(_.get).sortWith((e1,e2) => criterio(e1,e2)).head)
    
    misiones.find(_.serRealizadaPor(Resultado(equipo)) == mejorEstado)
  }
  
}

object CriteriosDeMision{
  type Criterio = (Equipo,Equipo) => Boolean
  
  val porMasOro = (equipo: Equipo, otroEquipo: Equipo) => equipo.pozo > otroEquipo.pozo
  
  val porMasHeroes = (equipo: Equipo, otroEquipo: Equipo) => equipo.integrantes.size > otroEquipo.integrantes.size
  
}
package main

case class Taberna(misiones: List[Mision] = List(abrirPuerta)) {
  import main.CriteriosDeMision._
  
  def elegirMejorMisionPara(equipo: Equipo)(criterio: Criterio) = {
    var mejorEstado = cabezaONada(equipo)(estadosOrdenadosDeEquipo(equipo, criterio))
    
    misiones.find(_.serRealizadaPor(Resultado(equipo)) == mejorEstado)
  }

  private def estadosOrdenadosDeEquipo(equipo: Equipo, criterio: Criterio) = {
    misiones.map(_.serRealizadaPor(Resultado(equipo))).filter(_.isSuccess).map(_.get).sortWith((e1,e2) => criterio(e1,e2))
  }
  
  private def cabezaONada(equipo: Equipo)(lista: List[Equipo]) = Resultado(lista.headOption.fold(equipo)(eq => eq))
}

object CriteriosDeMision{
  type Criterio = (Equipo,Equipo) => Boolean
  
  val porMasOro = (equipo: Equipo, otroEquipo: Equipo) => equipo.pozo > otroEquipo.pozo
  
  val porMasHeroes = (equipo: Equipo, otroEquipo: Equipo) => equipo.integrantes.size > otroEquipo.integrantes.size
  
}
package main

case class Taberna(misiones: List[Mision] = List(abrirPuerta)) {
  import main.CriteriosDeMision._
  
  def elegirMejorMisionPara(equipo: Equipo,criterio: Criterio)(listaMision:List[Mision]=misiones) = {
    var mejorEstado = cabezaONada(equipo)(estadosOrdenadosDeEquipo(equipo, criterio,listaMision))
    
    misiones.find(_.serRealizadaPor(Resultado(equipo)) == mejorEstado)
  }
  

  
  def entrenar(criterio:Criterio,equipo:Equipo) = { 
        funcRecursiva(criterio, equipo, misiones)
  }

  def funcRecursiva(criterio:Criterio,equipo:Equipo,listaMisiones:List[Mision]): Equipo ={
    val mision = elegirMejorMisionPara(equipo,criterio)(listaMisiones)
    if(mision.isDefined) {
      val nuevoEquipo = mision.fold(Resultado(equipo))(_.serRealizadaPor(Resultado(equipo)))
      funcRecursiva(criterio,nuevoEquipo.get, listaMisiones.filterNot(x => mision.fold(false)(_.equals(x))))
    }else{
      return equipo
    }
  }
  
  private def estadosOrdenadosDeEquipo(equipo: Equipo, criterio: Criterio,listaMision:List[Mision]) = {
    listaMision.map(_.serRealizadaPor(Resultado(equipo))).filter(_.isSuccess).map(_.get).sortWith((e1,e2) => criterio(e1,e2))
  }
  
  private def cabezaONada(equipo: Equipo)(lista: List[Equipo]) = Resultado(lista.headOption.fold(equipo)(eq => eq))
}

object CriteriosDeMision{
  type Criterio = (Equipo,Equipo) => Boolean
  
  val porMasOro = (equipo: Equipo, otroEquipo: Equipo) => equipo.pozo > otroEquipo.pozo
  
  val porMasHeroes = (equipo: Equipo, otroEquipo: Equipo) => equipo.integrantes.size > otroEquipo.integrantes.size
  
}
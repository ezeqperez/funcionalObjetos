package main

import scala.math.Ordered._

class Equipo(nombre: String = "", var integrantes: Option[List[Heroe]] = None, var pozo: Int = 0) {
  
  def mejorHeroeSegun(criterio: (Heroe => Int)) : Heroe = {
    modificarListaIntegrantesCon(identity).maxBy(criterio)
  }
  
  def obtenerItem(item: Item)(lista: List[Heroe]) = {
    if(lista.isEmpty)
      pozo = pozo + item.precio
    else
      modificarListaIntegrantesCon(losQueSirvenPara(item)).maxBy(diferenciaConMainStatDe(item))    //falta agregarle el item
  }
  
  def obtenerMiembro(nuevo: Heroe) = {
    integrantes = Some(modificarListaIntegrantesCon(anexarHeroe(nuevo)))
  }
  
  def reemplazarMiembro(viejo: Heroe, nuevo: Heroe) = {
    if(!integrantes.isEmpty) {
      quitarMiembro(viejo)
      obtenerMiembro(nuevo)
    }
  }
  
  def lider() = integrantes.map(_.maxBy(_.statPrincipal))  //puede haber o no un lider, es un Option
  
  
  
  private def losQueTrabajan(lista: List[Heroe]) = {
    lista.filterNot(_.statPrincipal.isEmpty)
  }
  
  private def puedeServirlesUn(item: Item)(lista: List[Heroe]) = {
    lista.filter(diferenciaConMainStatDe(item)(_) > 1)
  }
  
  private val losQueSirvenPara = (item: Item) => puedeServirlesUn(item) _ compose losQueTrabajan _
  
  private def diferenciaConMainStatDe(item: Item)(heroe: Heroe) = {
    heroe.copy().probar(item).statPrincipal - heroe.statPrincipal.getOrElse(0)    //FIXME, mal calculo, para salir del paso
  }
  
  
  private def modificarListaIntegrantesCon(funcion: List[Heroe] => List[Heroe])= {
    integrantes.fold[List[Heroe]](List()){funcion(_)}
  }
  
  private def anexarHeroe(heroe: Heroe)(lista: List[Heroe]) = {
    lista.+:(heroe)
  }
  
  private def removerHeroe(heroe: Heroe)(lista: List[Heroe]) = {
    lista.filterNot(_.equals(heroe))
  }
  
  private def quitarMiembro(viejo: Heroe) = {
    integrantes = Some(modificarListaIntegrantesCon(removerHeroe(viejo)))
  }
}
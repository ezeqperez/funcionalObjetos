package main

import scala.collection.mutable.ListBuffer
import main._

object Inventario {  
  def apply (items:List[Item]=List(),heroe:Heroe) : Inventario = {
    return crearInventario(items, heroe)
  }
  
  def crearInventario(items:List[Item],heroe:Heroe):Inventario={
    if(!items.forall(item=> item.puedeEquiparseEn(heroe)) || !sinExtras(items)){
      return new Inventario(List(),heroe)
    }
    return new Inventario(items,heroe)
  }
  
  def sinExtras(items: List[Item]): Boolean = {
      for (i: Item <- items) {
       i match{
         case Mano(1) => if(items.count(item => item.sosMiTipo(i)) > 2 || items.exists(item => item.sosMiTipo(espadaDeLaVida))){return false}  //FIXME es Mano(2) en vez de espadaDeLaVida
         case Mano(2) => if(items.count(item => item.sosMiTipo(i)) != 1 || items.exists(item => item.sosMiTipo(escudoAntiRobo))){return false} //FIXME es Mano(1) en vez de escudoAntiRobo
         case _ => if(items.count(item => item.sosMiTipo(i)) != 1) {return false}
       }
      }
      return true
    }
}

case class Inventario private(val items: List[Item] = List(), val heroe: Heroe){

      
      //mapea los items a su funcion de efecto, la reduce en un fold de composicion, y la aplica al stat del parametro
  def listoParaEquiparEn(heroe: Heroe)(stat: Stat) : Stat ={
    items.map(i => i.efectoPara(heroe)_).foldLeft(stat)((semilla: Stat,f: Stat => Stat) => f(semilla))
  }

  def agregarItem(nuevoItem: Item): List[Item] = {
    var nuevos = items.filterNot(item => item.sosMiTipo(nuevoItem)).:+(nuevoItem)
    nuevoItem match {
      case Mano(1) => nuevos.:+(items.find(item => item.sosMiTipo(nuevoItem)).getOrElse(List()))
      case _  =>
    }
    return nuevos
  }
}

package main

import scala.collection.mutable.ListBuffer
import main._

object Inventario {  
  def apply (items: List[Item] = List(), heroe: Heroe) : Inventario = {
    val inventario = Inventario(List(),heroe)
    
    return items.filter(_.puedeEquiparseEn(heroe)).foldLeft(inventario)((s: Inventario,item: Item) => s.agregarItem(item))
   }
}

case class Inventario private(val items: List[Item] = List(), val heroe: Heroe){

      
      //mapea los items a su funcion de efecto, la reduce en un fold, y la aplica al stat del parametro
  def listoParaEquiparEn(heroe: Heroe)(stat: Stat) : Stat ={
    items.map(i => i.efectoPara(heroe)_).foldLeft(stat)((semilla: Stat,f: Stat => Stat) => f(semilla))
  }

  def agregarItem(nuevoItem: Item): Inventario = {
    var nuevos = items.filterNot(item => item.sosMiTipo(nuevoItem)).:+(nuevoItem)
    
    nuevoItem match {
      case Mano(1) => this.copy(items = agregarDescartadoManoUno(nuevoItem, nuevos))
      case _  => this
    }
  }
  
  def agregarDescartadoManoUno(nuevo: Item, nuevosItems: List[Item]) = {
    var descartado = items.find(item => item.sosMiTipo(nuevo))
    
    descartado match {
      case Some(Mano(1)) => nuevosItems.+:(descartado.get)
      case _ => nuevosItems
    }
  }
}

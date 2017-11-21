package main

import scala.collection.mutable.ListBuffer
import main._

case class Inventario(val items: List[Item] = List()){

  def crearInventario(lista: List[Item]): Inventario = {
    var inv: Inventario = new Inventario(List())
    for (i: Item <- lista) {
      inv = inv.copy(agregarItem(i))
    }
    return inv
  }

  def equipar(item: Item, heroe: Heroe): Inventario = {
    if (item.puedeEquiparseEn(heroe)) {
      item.efectoPara(heroe)(heroe.stats)
      this.crearInventario(agregarItem(item))
    }
    else
      this
  }
  
      //mapea los items a su funcion de efecto, la reduce en un fold de composicion, y la aplica al stat del parametro
  def listoParaEquiparEn(heroe: Heroe)(stat: Stat) : Stat = items.map(i => i.efectoPara(heroe)_).reduce((x,y) => y compose x)(stat)

  def agregarItem(nuevoItem: Item): List[Item] = {
    var nuevos = items.filterNot(item => item.sosMiTipo(nuevoItem)).::(nuevoItem)
    nuevoItem match {
      case Mano(1) => nuevos.::(items.find(item => item.sosMiTipo(nuevoItem)).getOrElse(List()))
    }
    return nuevos
  }
}

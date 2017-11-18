package main

import scala.collection.mutable.ListBuffer
import main._

case class Inventario(var items: List[Item] = List()){

  def crearInventario(lista: List[Item]): Inventario = {
    var inv: Inventario = new Inventario(List())
    for (i: Item <- lista) {
      inv = inv.copy(agregarItem(i))
    }
    return inv
  }

  def equipar(item: Item, heroe: Heroe): Inventario = {
    if (item.puedeEquiparseEn(heroe)) {
      item.efectoPara(heroe, heroe.stats)
      return this.crearInventario(agregarItem(item))
    }
    return this
  }

  def agregarItem(nuevoItem: Item): List[Item] = {
    var nuevos = items.filterNot(item => item.sosMiTipo(nuevoItem)).::(nuevoItem)
    nuevoItem match {
      case Mano(1) => nuevos.::(items.find(item => item.sosMiTipo(nuevoItem)).getOrElse(List()))
    }
    return nuevos
  }
}

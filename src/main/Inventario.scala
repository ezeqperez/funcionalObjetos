package main

import main._

object Inventario {  
  
  def apply (items: List[Item] = List(), heroe: Heroe = new Heroe) : Inventario = {
    new Inventario(setearListaDe(items, heroe),heroe)
  }
  
  private def setearListaDe(items: List[Item], heroe: Heroe) = {
    List(cascoVikingo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,
        talismanMinimalismo)
    
    val inv = new Inventario(items,heroe)
    val listaVacia: List[Item] = List()
    
    items.filter(_.puedeEquiparseEn(heroe)).foldLeft(listaVacia)((_,item) => inv.nuevaListaDeItemsCon(item))
  }
}

case class Inventario private(items: List[Item] = List(), heroe: Heroe){

  def copy(items: List[Item] = this.items, heroe: Heroe = this.heroe): Inventario = {
    Inventario(items,heroe)
  }
   
        //mapea los items a su funcion de efecto, la reduce en un fold, y la aplica al stat del parametro
  def listoParaEquiparEn(heroe: Heroe)(stat: Stat) : Stat ={
    items.foldLeft(stat)((semilla: Stat,f: Item) => f.efectoPara(heroe)(semilla))
  }

  def agregarItem(nuevoItem: Item): Inventario = {
    this.copy(nuevaListaDeItemsCon(nuevoItem))
  }
  
  private def nuevaListaDeItemsCon(nuevoItem: Item) = {
    var nuevos = descartarLosDelTipoDe(nuevoItem)
    
    nuevoItem match {
      case Mano(1) => agregarDescartadoManoUno(nuevoItem, nuevos)
      case _  => nuevos
    }
  }
  
  private def agregarDescartadoManoUno(nuevo: Item, nuevosItems: List[Item]) = {
    var descartado = items.find(_.sosMiTipo(nuevo))
    
    descartado match {
      case Some(Mano(1)) => nuevosItems.+:(descartado.get)
      case _ => nuevosItems
    }
  }
  
  private def descartarLosDelTipoDe(item: Item) = items.filterNot(_.sosMiTipo(item)).:+(item)
}

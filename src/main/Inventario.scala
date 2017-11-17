package main

import scala.collection.mutable.ListBuffer
import main._

class Inventario(var items: Option[List[Item]] = None) {
  
  var equipo = itemsSinRepetidos(items.fold[List[Item]](List()) {identity(_)})

  def puedeEquiparse(unItem: Item, heroe : Heroe): Boolean = {
    heroe.teSirve(unItem)
  }
  def itemsSinRepetidos(items: List[Item]): List[Item] = {
    var itemsDefinitivos: List[Item] = List()
    List(Casco, Torso, Talisman, UnaMano).foreach(x =>
      x match {
        case Casco    => itemsDefinitivos = itemsDefinitivos ::: (eleccion(items.filter(item => item.isInstanceOf[Casco])))
        case Torso    => itemsDefinitivos = itemsDefinitivos ::: (eleccion(items.filter(item => item.isInstanceOf[Torso])))
        case Talisman => itemsDefinitivos = itemsDefinitivos ::: items.filter(item => item.isInstanceOf[Talisman])
        case _ => itemsDefinitivos = itemsDefinitivos ::: eleccionMano(items.filter(item => item.isInstanceOf[UnaMano] || item.isInstanceOf[DosManos]))
      })
      return itemsDefinitivos

  }

  def eleccion(items: List[Item]): List[Item] = {
    if (!items.isEmpty) {
      return List(items.tail(0))
    }
    return List()
  }

  def eleccionMano(items: List[Item]): List[Item] = {
    if (!items.isEmpty) {
      return List(items.tail(0)).:::(sonManos(items))
    }
    return List()
  }
  def sonManos(items: List[Item]) : List[Item] = {
    if (items.tail(0).isInstanceOf[UnaMano] && items.length > 1) {
      return esUnaMano(items.tail(1))
    }
    return List()
  }
  def esUnaMano(item : Item) : List[Item] ={
    if (item.isInstanceOf[UnaMano]) {
      return List(item)
    }
    return List()
  }
}

trait Item {
  def puedeEquiparseEn(heroe: Heroe): Boolean = true

  val efectoPara: (Heroe, Stat) => Stat //obligo a parametrizar la funcion que modifica los stats
}

abstract case class Casco(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class Torso(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class UnaMano(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class DosManos(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class Talisman(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
/*
object cascoVikingo extends Casco({ (_, s) => s.copy(hp = s.hp + 30) }) {
  override def puedeEquiparseEn(heroe: Heroe): Boolean = {
    return heroe.fuerzaBase > 30
  }
}

object palitoMagico extends Mano({ (_, s) => s.copy(inteligencia = s.inteligencia + 20) }) {
  override def puedeEquiparseEn(heroe: Heroe): Boolean = {
    heroe.trabajo match {
      case None    => false
      case Some(_) => apto(heroe)
    }
  }

  private def apto(heroe: Heroe): Boolean = {
    heroe.trabajo.get match {
      case Mago                                    => true
      case Ladron if (heroe.inteligenciaBase > 30) => true
      case _                                       => false
    }
  }
}

object armaduraEleganteSport extends Torso({ (_, s) => s.copy(hp = s.hp - 30, velocidad = s.velocidad + 30) })

object arcoViejo extends Mano({ (h, s) => s.copy(fuerza = s.fuerza + 2) })

object escudoAntiRobo extends Mano({ (h, s) => s.copy(hp = s.hp + 20) }) {
  override def puedeEquiparseEn(heroe: Heroe): Boolean = {
    heroe.trabajo match {
      case None    => false
      case Some(_) => apto(heroe)
    }
  }

  private def apto(heroe: Heroe): Boolean = {
    heroe.trabajo.get match {
      case Ladron                       => false
      case _ if (heroe.fuerzaBase < 20) => false
      case _                            => true
    }
  }
}

object talismanDedicacion extends Talisman {
  override val efectoPara: (Heroe, Stat) => Stat = (heroe, stat) => {

    val aumentoDe = (0.1 * (heroe.trabajo match {
      case Some(trabajo) => trabajo.statPrincipal
      case None          => 0
    })).toInt

    stat.copy(
      hp = stat.hp * aumentoDe,
      fuerza = stat.fuerza * aumentoDe,
      velocidad = stat.velocidad * aumentoDe,
      inteligencia = stat.inteligencia * aumentoDe)
  }
}

object talismanMinimalismo extends Talisman {
  override val efectoPara: (Heroe, Stat) => Stat = (heroe, stat) => {

    val vidaPerdida = heroe.inventario.equipamiento.length * 10

    stat.copy(
      hp = stat.hp - vidaPerdida,
      fuerza = stat.fuerza - vidaPerdida,
      velocidad = stat.velocidad - vidaPerdida,
      inteligencia = stat.inteligencia - vidaPerdida)
  }
}

object vinchaBufalo extends Casco {
  override def puedeEquiparseEn(heroe: Heroe): Boolean = {
    heroe.trabajo match {
      case None    => true
      case Some(_) => false
    }
  }

  override val efectoPara: (Heroe, Stat) => Stat = (heroe, stat) => {
    heroe match {
      case _ if (heroe.fuerzaBase > heroe.inteligenciaBase) => stat.copy(inteligencia = stat.inteligencia + 30)
      case _ => stat.copy(hp = stat.hp + 10, fuerza = stat.fuerza + 10, velocidad = stat.velocidad + 10)
    }
  }
}

object talismanMaldito extends Talisman({ (_, s) => s.copy(1, 1, 1, 1) })

object espadaDeLaVida extends Mano((_, s) => s.copy(fuerza = s.hp))*/
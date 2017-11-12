package main

import scala.collection.mutable.ListBuffer
import main._

class Inventario(var items: Option[List[Item]] = None, var heroe: Heroe) {
  var equipo = items.getOrElse(List()).filter(puedeEquiparse _)
  
  def puedeEquiparse(unItem: Item) : Boolean = {
    heroe.teSirve(unItem)
  }
  
  //FIXME ver manera de que haya un solo item de su tipo

}

trait Item {
  def puedeEquiparseEn(heroe: Heroe): Boolean = true

  val efectoPara: (Heroe, Stat) => Stat //obligo a parametrizar la funcion que modifica los stats
}

abstract case class Casco(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class Torso(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class Mano(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
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
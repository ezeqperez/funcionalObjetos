package main

import scala.collection.mutable.ListBuffer
import main._

class Inventario(var items: Option[List[Item]] = None, var heroe: Heroe) {
  var cabeza: Option[Item] = None
  var cuerpo: Option[Item] = None
  var manos: Option[Item] = None
  var talismanes: Option[List[Item]] = None

  items match {
    case Some(items) => this.equiparCon(items)
    case None        =>
  }

  def equiparUn(item: Item): Unit = {
    if (item.puedeEquiparseEn(heroe))
      item match {
        case item: Casco    => cabeza = Some(item)
        case item: Torso    => cuerpo = Some(item)
        case item: Mano     => manos = Some(item)       //FIXME falta lo de dos manos
        case item: Talisman => agregarTalisman(item)
      }
  }

  private def agregarTalisman(item: Talisman) = {
    talismanes = talismanes match {
      case Some(lista) => Some(lista.to[ListBuffer].+=(item).toList)
      case None        => Some(new ListBuffer().+=(item).toList)
    }
  }

  private def equiparCon(items: List[Item]): Unit = {
    items.foreach(item => this.equiparUn(item))
  }

  def equipamiento: List[Item] = {
    return List(cabeza, cuerpo, manos).filterNot(_.isEmpty).map(_.get).++(talismanes.getOrElse(List()))
  }

  def mergearStatsCon(statsIniciales: Stat): Stat = {
    return equipamiento.map(_.efectoPara.curried(heroe)).foldLeft(statsIniciales) { (semilla, funcion) => funcion(semilla) }
  }

}

trait Item {
  def puedeEquiparseEn(heroe: Heroe): Boolean = true

  val efectoPara: (Heroe, Stat) => Stat //obligo a parametrizar la funcion que modifica los stats
}

abstract case class Casco(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class Torso(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class Mano(val efectoPara: (Heroe, Stat) => Stat = null) extends Item
abstract case class Talisman(val efectoPara: (Heroe, Stat) => Stat = null) extends Item

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

object espadaDeLaVida extends Mano((_, s) => s.copy(fuerza = s.hp))
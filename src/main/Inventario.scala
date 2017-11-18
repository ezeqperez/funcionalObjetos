package main

import scala.collection.mutable.ListBuffer
import main._

class Inventario(var items: Option[List[Item]] = None, var heroe: Heroe) {

  def puedeEquiparse(unItem: Item): Boolean = {
    unItem.puedeEquiparseEn(heroe)
  }

  var itemsEquipados = itemsSinRepetidos(items.fold[List[Item]](List()) { identity(_) })

  def itemsSinRepetidos(items: List[Item]): List[Item] = {
    return eleccion(items.filter(item => item.isInstanceOf[Casco])) ::: eleccion(items.filter(item => item.isInstanceOf[Torso])) ::: items.filter(item => item.isInstanceOf[Talisman]) ::: eleccionMano(items.filter(item => item.isInstanceOf[Mano]))
  }

  def eleccion(items: List[Item]): List[Item] = {
    if (!items.isEmpty && puedeEquiparse(items.tail(0))) {
      return List(items.tail(0))
    } else if (!items.dropRight(1).isEmpty) {
      return eleccion(items.dropRight(1))
    }
    return List()
  }

  def eleccionMano(items: List[Item]): List[Item] = {
    if (!items.isEmpty && puedeEquiparse(items.tail(0))) {
      return List(items.tail(0)).:::(sonManos(items))
    } else if (!items.dropRight(1).isEmpty) {
      return eleccionMano(items.dropRight(1))
    }
    return List()
  }
  def sonManos(items: List[Item]): List[Item] = {
    if (items.length > 1 && items.tail(0).asInstanceOf[Mano].manosNecesarias == 1 && items.tail(1).asInstanceOf[Mano].manosNecesarias == 1 && puedeEquiparse(items.tail(1))) {
      return List(items.tail(1))
    }else if(items.length > 1){
      sonManos(items.dropRight(1))
    }
    return List()
  }
  
  
  def modificarInventario(unItem : Item){
    if(hayQueReemplazarPor(unItem)){
      reemplazarCon(unItem)
    }else{
      itemsEquipados.+:(unItem)
    }
  }
  def reemplazarCon(item : Item){
    itemsEquipados = itemsEquipados.filterNot(_.getClass.equals(item.getClass)).+:(item)
  }
  
  def hayQueReemplazarPor(item: Item) : Boolean = {
    return item match{
      case Casco() => yaHayDeTipoDe(item)
      case Torso() => yaHayDeTipoDe(item)
      case Mano(manos) => manosOcupadasPara(manos)
    }
  }
  
  def yaHayDeTipoDe(item: Item) : Boolean = {
    itemsEquipados.exists(_.getClass.equals(item.getClass))
  }
  
  def manosOcupadasPara(manos: Int): Boolean = {
    return manos match{
      case 1 => itemsEquipados.count(_.getClass.equals(Mano)) == 2
      case _ => true
    }
  }
  
}

trait Item {
  val precio = 0
  
  def puedeEquiparseEn(heroe: Heroe): Boolean = true

  def efectoPara(heroe: Heroe, stat: Stat): Stat = new Stat() //obligo a parametrizar la funcion que modifica los stats

}

abstract case class Casco() extends Item
abstract case class Torso() extends Item
abstract case class Mano(manosNecesarias : Int) extends Item
abstract case class Talisman() extends Item

object cascoVikingo extends Casco() {

  override def efectoPara(h: Heroe, s: Stat): Stat = {
    return s.copy(hp = s.hp + 30)
  }

}
object palitoMagico extends Mano(1) {

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

  override def efectoPara(heroe: Heroe, s: Stat): Stat = {
    return s.copy(inteligencia = s.inteligencia + 20)
  }
}

object armaduraEleganteSport extends Torso() {
  override def efectoPara(heroe: Heroe, s: Stat): Stat = {
    return s.copy(hp = 1.max(s.hp - 30), velocidad = s.velocidad + 30)
  }
}

object arcoViejo extends Mano(1) {
  override def efectoPara(heroe: Heroe, s: Stat): Stat = {
    return s.copy(fuerza = s.fuerza + 2)
  }
}

object escudoAntiRobo extends Mano(1) {
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

  override def efectoPara(heroe: Heroe, s: Stat): Stat = {
    return s.copy(hp = s.hp + 20)
  }
}

object talismanDedicacion extends Talisman {
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = {

    val aumentoDe = (0.1 * (heroe.trabajo match {
      case Some(trabajo) => trabajo.statPrincipal
      case None          => 0
    })).toInt

    return stat.copy(
      hp = stat.hp + aumentoDe,
      fuerza = stat.fuerza + aumentoDe,
      velocidad = stat.velocidad + aumentoDe,
      inteligencia = stat.inteligencia + aumentoDe)
  }
}

object talismanMinimalismo extends Talisman {
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = {

    val vidaPerdida = heroe.inventario.items.getOrElse(List()).length * 10

    return stat.copy(
      hp = 1.max(stat.hp - vidaPerdida),
      fuerza = 1.max(stat.fuerza - vidaPerdida),
      velocidad = 1.max(stat.velocidad - vidaPerdida),
      inteligencia = 1.max(stat.inteligencia - vidaPerdida))
  }
}

object vinchaBufalo extends Casco {
  override def puedeEquiparseEn(heroe: Heroe): Boolean = {
    heroe.trabajo match {
      case None    => true
      case Some(_) => false
    }
  }

  override def efectoPara(heroe: Heroe, stat: Stat): Stat = {
    heroe match {
      case _ if (heroe.fuerzaBase > heroe.inteligenciaBase) => return stat.copy(inteligencia = stat.inteligencia + 30)
      case _ => return stat.copy(hp = stat.hp + 10, fuerza = stat.fuerza + 10, velocidad = stat.velocidad + 10, inteligencia = stat.inteligencia + 10)
    }
  }
}

object talismanMaldito extends Talisman() {
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = {
    return stat.copy(1, 1, 1, 1)
  }

}

object espadaDeLaVida extends Mano(1) {

  override def efectoPara(heroe: Heroe, stat: Stat): Stat = {
    return stat.copy(fuerza = stat.hp)
  }
}
package main

import scala.collection.mutable.ListBuffer
import main._

class Inventario(var items: Option[List[Item]] = None, var heroe: Heroe) {
  var equipo = items.getOrElse(List()).filter(puedeEquiparse _)
  
  def puedeEquiparse(unItem: Item) : Boolean = {
    unItem.puedeEquiparseEn(heroe)
  }//FIXME ver manera de que haya un solo item de su tipo

}

trait Item {
  val precio = 0
  
  def puedeEquiparseEn(heroe: Heroe): Boolean = true

  def efectoPara(heroe: Heroe, stat: Stat): Stat = new Stat()//obligo a parametrizar la funcion que modifica los stats
  
}

abstract case class Casco() extends Item
abstract case class Torso() extends Item
abstract case class Mano() extends Item
abstract case class Talisman() extends Item


object cascoVikingo extends Casco() {
  
  override def efectoPara(h :Heroe, s: Stat): Stat = {
    return s.copy(hp=s.hp+30)
  }
  override def puedeEquiparseEn(heroe: Heroe): Boolean = {
    return heroe.fuerzaBase > 30
  }
}

object palitoMagico extends Mano() {
 
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

object armaduraEleganteSport extends Torso(){
  override def efectoPara(heroe: Heroe, s: Stat): Stat = {
    return s.copy(hp = 1.max(s.hp - 30), velocidad = s.velocidad + 30)
  }
}

object arcoViejo extends Mano(){
  override def efectoPara(heroe: Heroe, s: Stat): Stat ={
  return s.copy(fuerza = s.fuerza + 2)
  }
}

object escudoAntiRobo extends Mano() {
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
  
  override def efectoPara(heroe: Heroe, s: Stat): Stat ={
  return s.copy(hp = s.hp + 20)
  }
}

object talismanDedicacion extends Talisman {
  override def efectoPara(heroe: Heroe,stat: Stat): Stat = {

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
  override def efectoPara (heroe: Heroe, stat: Stat): Stat =  {

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

  override def efectoPara (heroe: Heroe, stat: Stat): Stat =  {
    heroe match {
      case _ if (heroe.fuerzaBase > heroe.inteligenciaBase) => return stat.copy(inteligencia = stat.inteligencia + 30)
      case _ => return stat.copy(hp = stat.hp + 10, fuerza = stat.fuerza + 10, velocidad = stat.velocidad + 10,inteligencia = stat.inteligencia + 10)
    }
  }
}

object talismanMaldito extends Talisman(){
  override def efectoPara (heroe: Heroe, stat: Stat): Stat =  {
    return stat.copy(1, 1, 1, 1)
  }
  
}

object espadaDeLaVida extends Mano(){
  
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = {
    return stat.copy(fuerza = stat.hp)
  }
}
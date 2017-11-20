package main

trait Item {
  val precio = 0

  def puedeEquiparseEn(heroe: Heroe): Boolean = true
  
  def efectoPara(heroe: Heroe, stat: Stat): Stat = new Stat() //obligo a parametrizar la funcion que modifica los stats  
  
  def sosMiTipo(item: Item): Boolean = {
    return item.getClass.eq(this.getClass)
  }
  
  
  protected def cambiarFuerzaEn (valor: Int)(stat: Stat) = stat.copy(fuerza = stat.fuerza + valor)
  protected def cambiarHpEn (valor: Int)(stat: Stat) = stat.copy(hp = stat.hp + valor)
  protected def cambiarVelocidadEn (valor: Int)(stat: Stat) = stat.copy(velocidad = stat.velocidad + valor)
  protected def cambiarInteligenciaEn (valor: Int)(stat: Stat) = stat.copy(inteligencia = stat.inteligencia + valor)
}

abstract case class Casco() extends Item
abstract case class Torso() extends Item
abstract case class Mano(manosNecesarias: Int) extends Item
abstract case class Talisman() extends Item

object cascoVikingo extends Casco() {
  
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = cambiarHpEn(30)(stat)
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

  override def efectoPara(heroe: Heroe, stat: Stat): Stat = cambiarInteligenciaEn(20) (stat)
}

object armaduraEleganteSport extends Torso() {
  
  def efectoPara = cambiarHpEn(-30)_ compose cambiarVelocidadEn(30)_
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = cambiarHpEn(-30)(cambiarVelocidadEn(30) (stat))
}

object arcoViejo extends Mano(1) {
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = cambiarHpEn(2) (stat)
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

 override def efectoPara(heroe: Heroe, stat: Stat): Stat = cambiarHpEn(20) (stat)
}

object talismanDedicacion extends Talisman {
  
  
    
  override def efectoPara(h :Heroe,s :Stat) :Stat = {
    
    val aumentoDe = (0.1 * (h.trabajo match {
      case Some(trabajo) => trabajo.statPrincipal
      case None          => 0
    })).toInt
    
   return cambiarFuerzaEn(aumentoDe)(cambiarInteligenciaEn(aumentoDe)(cambiarVelocidadEn(aumentoDe)(cambiarHpEn(aumentoDe) (s))))
  }
  
}

object talismanMinimalismo extends Talisman {
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = {

    val vidaPerdida = heroe.inventario.items.length * 10

     return cambiarFuerzaEn(-vidaPerdida)(cambiarInteligenciaEn(-vidaPerdida)(cambiarVelocidadEn(-vidaPerdida)(cambiarHpEn(-vidaPerdida) (stat))))
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
      case _ if (heroe.fuerzaBase > heroe.inteligenciaBase) =>  return cambiarInteligenciaEn(30) (stat)
      case _ => return cambiarFuerzaEn(10)(cambiarInteligenciaEn(10)(cambiarVelocidadEn(10)(cambiarHpEn(10) (stat))))
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
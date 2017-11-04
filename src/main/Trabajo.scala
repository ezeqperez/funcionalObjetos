package main.trabajo

import main.Heroe

trait Trabajo {
  val hp: Int
  val fuerza: Int
  val velocidad: Int
  val inteligencia: Int

  def cambiarTrabajo(heroe: Heroe): Heroe = {
    return heroe.copy(hpBase = heroe.hpBase + hp, 
                      fuerzaBase = heroe.fuerzaBase + fuerza,
                      velocidadBase = heroe.velocidadBase + velocidad, 
                      inteligenciaBase = heroe.inteligenciaBase + inteligencia,
                      trabajo = Option(this))
  }
}

object Guerrero extends Trabajo {
  val hp = 10
  val fuerza = 15
  val velocidad = 0
  val inteligencia = -10
}

object Mago extends Trabajo {
  val hp = 0
  val fuerza = -20
  val velocidad = 0
  val inteligencia = 20
}

object Ladron extends Trabajo {
  val hp = -5
  val fuerza = 0
  val velocidad = 10
  val inteligencia = 0
}
/*case object Ladron extends Trabajo {
    override def modificarStats(heroe : Heroe) : Heroe = {
    return heroe.copy(fuerzaBase = (heroe.fuerzaBase - 5).max(1), velocidadBase = (heroe.velocidadBase + 10).max(1))
  }
}*/
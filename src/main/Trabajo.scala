package main.trabajo

import main._

trait Trabajo {
  val hp: Int
  val fuerza: Int
  val velocidad: Int
  val inteligencia: Int

  def stats : Stat = {
    return new Stat(hp,fuerza,velocidad,inteligencia)
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
package main

import main._

trait Trabajo {
  val hp: Int
  val fuerza: Int
  val velocidad: Int
  val inteligencia: Int

  def stats : Stat = {
    return new Stat(hp,fuerza,velocidad,inteligencia)
  }
  
  def statPrincipal : Int = {
    return List(hp,fuerza,velocidad,inteligencia).max
  }
}

case object Guerrero extends Trabajo {
  val hp = 10
  val fuerza = 15
  val velocidad = 0
  val inteligencia = -10
}

case object Mago extends Trabajo {
  val hp = 0
  val fuerza = -20
  val velocidad = 0
  val inteligencia = 20
}

case object Ladron extends Trabajo {
  val hp = -5
  val fuerza = 0
  val velocidad = 10
  val inteligencia = 0
}
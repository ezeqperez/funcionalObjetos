package main

import main._

sealed trait Trabajo {
  val stats: Stat

  def statPrincipal = stats.statPrincipal
  
  val cambiarFuerzaEn = (valor: Int, stat: Stat) => stat.copy(fuerza = stat.fuerza + valor)
}

case object Guerrero extends Trabajo {
  val stats = new Stat(hp = 10, fuerza = 15, inteligencia = -10)
}

case object Mago extends Trabajo {
  val stats = new Stat(fuerza = -20, inteligencia = 20)
}

case object Ladron extends Trabajo {
  val stats = new Stat(hp = -5, velocidad = 10)
}
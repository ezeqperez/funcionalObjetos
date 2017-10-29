package main.trabajo

import main.Heroe

trait Trabajo{
  val statPrincipal : String //TODO volar estos string feos..
}

object Guerrero extends Trabajo{
  val statPrincipal = "Fuerza"
  val hp            = 10
  val fuerza        = 15
  val inteligencia  = -10
  
  def cambiarTrabajo(heroe : Heroe) : Heroe = {
    return heroe.copy(hpBase = heroe.hpBase + hp, fuerzaBase = heroe.fuerzaBase + fuerza, 
        inteligenciaBase = heroe.inteligenciaBase + inteligencia, trabajo = Option(this))
  }
}

object Mago extends Trabajo{
  val statPrincipal = "Inteligencia"
  val fuerza        = -20
  val inteligencia  = 20
  
  def cambiarTrabajo(heroe : Heroe) : Heroe = {
    return heroe.copy(heroe.fuerzaBase + fuerza, inteligenciaBase = heroe.inteligenciaBase + inteligencia, trabajo = Option(this))
  }
}

object Ladron extends Trabajo{
  val statPrincipal = "Velocidad"
  val hp            = -5
  val velocidad     = 10
  
  def cambiarTrabajo(heroe : Heroe) : Heroe = {
    return heroe.copy(hpBase = heroe.hpBase + hp, velocidadBase = heroe.velocidadBase + velocidad, trabajo = Option(this))
  }
}
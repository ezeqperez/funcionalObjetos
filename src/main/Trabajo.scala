package main.trabajo

import main.Heroe

trait Trabajo {
  
  def modificarStats(heroe: Heroe) : Heroe
}

case object Guerrero extends Trabajo {
  
  override def modificarStats(heroe : Heroe) : Heroe = {
    return heroe.copy(hpBase = heroe.hpBase + 10, fuerzaBase = heroe.fuerzaBase + 15, inteligenciaBase = heroe.inteligenciaBase -10)
  }
}

case object Mago extends Trabajo {
  
  override def modificarStats(heroe : Heroe) : Heroe = {
    return heroe.copy(fuerzaBase = heroe.fuerzaBase - 20, inteligenciaBase = heroe.inteligenciaBase + 20)
  }
}

case object Ladron extends Trabajo {
  
  override def modificarStats(heroe : Heroe) : Heroe = {
    return heroe.copy(fuerzaBase = heroe.fuerzaBase - 5, velocidadBase = heroe.velocidadBase + 10)
  }
}
package main

import main._

sealed trait Trabajo {
  def statPrincipal : Int
  def apply : Stat => Stat
  
  protected def cambiarFuerzaEn (valor: Int)(stat: Stat) = stat.copy(fuerza = stat.fuerza + valor)
  protected def cambiarHpEn (valor: Int)(stat: Stat) = stat.copy(hp = stat.hp + valor)
  protected def cambiarVelocidadEn (valor: Int)(stat: Stat) = stat.copy(velocidad = stat.velocidad + valor)
  protected def cambiarInteligenciaEn (valor: Int)(stat: Stat) = stat.copy(inteligencia = stat.inteligencia + valor)

}

case object Guerrero extends Trabajo {
  def statPrincipal = 15
  
  def apply = cambiarHpEn(10)_ compose cambiarFuerzaEn(15)_ compose cambiarInteligenciaEn(-10)_
}

case object Mago extends Trabajo {
  def statPrincipal = 20
  
  def apply = cambiarFuerzaEn(-20)_ compose cambiarInteligenciaEn(20)_
}

case object Ladron extends Trabajo {
  def statPrincipal = 15
  
  def apply = cambiarHpEn(-5)_ compose cambiarVelocidadEn(10)_
}
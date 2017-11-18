package main

case class Stat(hp: Int = 1, fuerza: Int = 1, velocidad: Int = 1, inteligencia: Int = 1) {
  this.copy(1.max(hp), 1.max(fuerza), 1.max(velocidad), 1.max(inteligencia))
  
  
  def +(stat: Stat) : Stat = {
    return this.copy(1.max(hp + stat.hp), 1.max(fuerza + stat.fuerza), 1.max(velocidad + stat.velocidad), 1.max(inteligencia + stat.inteligencia))
  }
  
  def statPrincipal : Int = {
    return List(hp,fuerza,velocidad,inteligencia).max
  }
  
}
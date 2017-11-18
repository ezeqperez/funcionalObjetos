package main

case class Stat(hp: Int = 0, fuerza: Int = 0, velocidad: Int = 0, inteligencia: Int = 0) {
  
  def +(stat: Stat) : Stat = {
    return this.copy(hp + stat.hp, fuerza + stat.fuerza, velocidad + stat.velocidad, inteligencia + stat.inteligencia)
  }
  
  def statPrincipal : Int = {
    return List(hp,fuerza,velocidad,inteligencia).max
  }
}
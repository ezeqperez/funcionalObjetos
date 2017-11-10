package main

case class Stat(hp: Int = 0, fuerza: Int = 0, velocidad: Int = 0, inteligencia: Int = 0) {
  
  def mergarCon(stat: Stat) : Stat = {
    return this.copy(1.max(hp + stat.hp), 1.max(fuerza + stat.fuerza), 1.max(velocidad + stat.velocidad), 1.max(inteligencia + stat.inteligencia))
  }
}
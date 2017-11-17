package main

case class Stat(hp: Int = 1, fuerza: Int = 1, velocidad: Int = 1, inteligencia: Int = 1) {
  require(hp > 0, "El hp debe ser mayor a cero")
  require(fuerza > 0, "La fuerza debe ser mayor a cero")
  require(velocidad > 0, "La velocidad debe ser mayor a cero")
  require(inteligencia > 0, "La inteligencia debe ser mayor a cero")
  
  
  def +(stat: Stat) : Stat = {
    return this.copy(1.max(hp + stat.hp), 1.max(fuerza + stat.fuerza), 1.max(velocidad + stat.velocidad), 1.max(inteligencia + stat.inteligencia))
  }
  
  def statPrincipal : Int = {
    return List(hp,fuerza,velocidad,inteligencia).max
  }
  
  def getFuerza : Int={
    return fuerza
  }
  
  def getHp : Int = {
    return hp
  }
  
  def getVelocidad : Int = {
    return velocidad
  }
  
  def getInteligencia : Int = {
    return inteligencia
  }
  
}
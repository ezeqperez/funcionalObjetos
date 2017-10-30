package main

import main.trabajo.Trabajo

class Heroe(var hpBase: Int, var fuerzaBase: Int, var velocidadBase: Int, var inteligenciaBase: Int, var trabajo: Option[Trabajo],
                 var inventario: List[Item]) {
  require(hpBase > 0, "El hp debe ser mayor a cero")
  require(fuerzaBase > 0, "La fuerza debe ser mayor a cero")
  require(velocidadBase > 0, "La velocidad debe ser mayor a cero")
  require(inteligenciaBase > 0, "La inteligencia debe ser mayor a cero")

  def this(hpBase: Int, fuerzaBase: Int, velocidadBase: Int, inteligenciaBase: Int) =
    this(hpBase, fuerzaBase, velocidadBase, inteligenciaBase, None, Nil);
  
  def copy(hpBase: Int, fuerzaBase: Int, velocidadBase: Int, inteligenciaBase: Int) : Heroe = {
    return new Heroe(hpBase.max(1), fuerzaBase.max(1), velocidadBase.max(1), inteligenciaBase.max(1))
  }
  
  def copy(hpBase: Int, fuerzaBase: Int, velocidadBase: Int, inteligenciaBase: Int, trabajo: Option[Trabajo]) : Heroe = {
    return new Heroe(hpBase.max(1), fuerzaBase.max(1), velocidadBase.max(1), inteligenciaBase.max(1), trabajo, Nil)
  }
  
  def copy(hpBase: Int, fuerzaBase: Int, velocidadBase: Int, inteligenciaBase: Int, inventario: List[Item]) : Heroe = {
    return new Heroe(hpBase.max(1), fuerzaBase.max(1), velocidadBase.max(1), inteligenciaBase.max(1), None, inventario)
  }
  
  def copy(hpBase: Int, fuerzaBase: Int, velocidadBase: Int, inteligenciaBase: Int, trabajo: Option[Trabajo], inventario: List[Item]) : Heroe = {
    return new Heroe(hpBase.max(1), fuerzaBase.max(1), velocidadBase.max(1), inteligenciaBase.max(1), trabajo, inventario)
  }
}

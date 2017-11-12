package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main._
import main.trabajo.Mago
import main.trabajo.Ladron
import main.trabajo.Guerrero
import junit.framework.AssertionFailedError

class TestHeroe {
  var heroe: Heroe = null

  @Before
  def setUp() {
    heroe = new Heroe(7, 4, 3, 2)
  }

  @Test
  def crearHeroe() {
    assertEquals(7, heroe.hpBase)
    assertEquals(4, heroe.fuerzaBase)
    assertEquals(3, heroe.velocidadBase)
    assertEquals(2, heroe.inteligenciaBase)
    assertEquals(None, heroe.trabajo)
    //assertEquals(true, heroe.inventario.isEmpty)
  }

  @Test
  def statsPositivos() {
    /* tirar excepcion
    intercept[IllegalArgumentException] {
      heroeInvalido = new Heroe(-1,3,4,5)
    }
    */
  }
  
/*  @Test
  def base() {
    heroe = new Heroe(fuerzaBase = 10, trabajo = Some(Ladron))
    assertEquals(10, heroe.fuerzaBase)
  }
  
  @Test
  def conTrabajo() {
    heroe = new Heroe(fuerzaBase = 10, trabajo = Some(Ladron))
    assertEquals(10, heroe.fuerzaBase)
    assertEquals(5, heroe.equiparHeroe.fuerzaBase)
  }*/

 /* @Test
  def trabajoMago() {
    val heroeMago: Heroe = Mago.cambiarTrabajo(heroe)
    assertEquals(7, heroeMago.hpBase)
    assertEquals(1, heroeMago.fuerzaBase)
    assertEquals(3, heroeMago.velocidadBase)
    assertEquals(22, heroeMago.inteligenciaBase)
    assertEquals(Option(Mago), heroeMago.trabajo)
    assertEquals(true, heroeMago.inventario.isEmpty)
  }

  @Test
  def trabajoGuerrero() {
    val heroeGuerrero: Heroe = Guerrero.cambiarTrabajo(heroe)
    assertEquals(17, heroeGuerrero.hpBase)
    assertEquals(19, heroeGuerrero.fuerzaBase)
    assertEquals(3, heroeGuerrero.velocidadBase)
    assertEquals(1, heroeGuerrero.inteligenciaBase)
    assertEquals(Option(Guerrero), heroeGuerrero.trabajo)
    assertEquals(true, heroeGuerrero.inventario.isEmpty)
  }

  @Test
  def trabajoLadron() {
    val heroeLadron: Heroe = Ladron.cambiarTrabajo(heroe)
    assertEquals(2, heroeLadron.hpBase)
    assertEquals(4, heroeLadron.fuerzaBase)
    assertEquals(13, heroeLadron.velocidadBase)
    assertEquals(2, heroeLadron.inteligenciaBase)
    assertEquals(Option(Ladron), heroeLadron.trabajo)
    assertEquals(true, heroeLadron.inventario.isEmpty)
  }

  @Test
  def equiparItem() {
    val heroeEquipado = EspadaDeLaVida.equipar(heroe)
    assertEquals(heroeEquipado.fuerzaBase, heroeEquipado.hpBase)
    assertEquals(true, heroeEquipado.inventario.contains(EspadaDeLaVida))
    assertEquals(1, heroeEquipado.inventario.length)
  }*/
}
package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main._
import junit.framework.AssertionFailedError

class TestHeroe {
  var statBase: Stat = null
  var heroe: Heroe = null
  var ladron: Heroe = null

  @Before
  def setUp() {
    statBase = new Stat(fuerza = 3, hp = 1, inteligencia = 2, velocidad = 10)
    heroe = new Heroe(statBase)
    ladron = new Heroe(statBase, trabajo = Some(Ladron))
  }

  @Test
  def inventario() {
    var inv = new Inventario(List(cascoVikingo, armaduraEleganteSport))
    assertEquals(false, inv.items.isEmpty)
  }
  
  @Test
  def crearHeroe() {
    assertEquals(3, heroe.getFuerza)
    assertEquals(1, heroe.getHp)
    assertEquals(2, heroe.getInteligencia)
    assertEquals(10, heroe.getVelocidad)
    assertEquals(None, heroe.trabajo)
    assertEquals(true, heroe.inventario.items.isEmpty)
  }

  @Test
  def conTrabajo() {
    assertEquals(3, ladron.getFuerza)
    assertEquals(1, ladron.getHp)
    assertEquals(2, ladron.getInteligencia)
    assertEquals(20, ladron.getVelocidad)
    assertEquals(Option(Ladron), ladron.trabajo)
  }

  @Test
  def trabajoMago() {
    var mago = ladron.cambiarTrabajo(Mago)
    assertEquals(1, mago.getFuerza)
    assertEquals(1, mago.getHp)
    assertEquals(22, mago.getInteligencia)
    assertEquals(10, mago.getVelocidad)
    assertEquals(Option(Mago), mago.trabajo)
  }

  @Test
  def trabajoGuerrero() {
    val guerrero: Heroe = heroe.cambiarTrabajo(Guerrero)
    assertEquals(18, guerrero.getFuerza)
    assertEquals(11, guerrero.getHp)
    assertEquals(1, guerrero.getInteligencia)
    assertEquals(10, guerrero.getVelocidad)
    assertEquals(Option(Guerrero), guerrero.trabajo)
  }

  @Test
  def equiparItem() {
    val heroeEquipado : Heroe = heroe.equipar(espadaDeLaVida)
    //assertEquals(heroeEquipado.getFuerza, heroeEquipado.getHp)
    assertEquals(true, heroeEquipado.inventario.items.contains(espadaDeLaVida))
    assertEquals(1, heroeEquipado.inventario.items.length)
  }

  @Test
  def testeoDeObjetos() {
    assertEquals(new Stat(31, 3, 10, 2), cascoVikingo.efectoPara(heroe, statBase))
    assertEquals(new Stat(1, 3, 40, 2), armaduraEleganteSport.efectoPara(heroe, statBase))
    assertEquals(new Stat(1, 3, 10, 32), vinchaBufalo.efectoPara(heroe, statBase))
  }
}
package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main._
import junit.framework.AssertionFailedError

class TestHeroe {
  var statBase : Stat = null
  var statBase2 : Stat = null
  var heroe : Heroe = null
  var listaItems : Option[List[Item]] = null
  var statEsperado : Stat = null
  var statEsperado2 : Stat = null
  var statEsperado3 : Stat = null
  
  
  @Before
  def setUp() {
    statBase = new Stat()
    statBase2 = new Stat(10,10,10,10)
    listaItems = Some(List(cascoVikingo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,talismanMinimalismo,vinchaBufalo,palitoMagico,escudoAntiRobo,espadaDeLaVida))
    heroe = new Heroe(statBase,None,listaItems)
    statEsperado = statBase.copy(hp = statBase.hp + 30)
    statEsperado2 = statBase.copy(velocidad = statBase.velocidad + 30)
    statEsperado3 = new Stat(10,10,10,10)
  }

  @Test
  def testeoDeObjetos() {
    assertEquals(new Stat(31,1,1,1), cascoVikingo.efectoPara(heroe,statBase))
    assertEquals(new Stat(1,1,31,1), armaduraEleganteSport.efectoPara(heroe,statBase))
    //assertEquals(new Stat(2,2,2,2), talismanDedicacion.efectoPara(heroe,statBase))
    assertEquals(new Stat(11,11,11,11), vinchaBufalo .efectoPara(heroe,statBase))
   

  }


  @Test
  def crearHeroe() {
    assertEquals(7, heroe.statsIniciales.hp)
    assertEquals(4, heroe.statsIniciales.fuerza)
    assertEquals(3, heroe.statsIniciales.velocidad)
    assertEquals(2, heroe.statsIniciales.inteligencia)
    assertEquals(None, heroe.trabajo)
    //assertEquals(true, heroe.inventario.isEmpty)

  }
/*
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
*/}
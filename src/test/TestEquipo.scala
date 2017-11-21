package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import main._
import junit.framework.AssertionFailedError

class TestEquipo {
  val statsBuenos = Stat(20, 20, 20, 20)
  val statsMalos = Stat(1, 1, 1, 1)

  var inventario: Inventario = new Inventario(List(cascoVikingo, armaduraEleganteSport))

  var heroeBueno: Heroe = null
  val heroeMalo = new Heroe(statsMalos)

  var equipo: Equipo = null

  @Before
  def setUp() {
    equipo = new Equipo(integrantes = List(heroeBueno, heroeMalo))
    heroeBueno = new Heroe(statsBuenos, Some(Ladron), inventario)
  }

  @Test
  def mejorHeroeSegunStatPrincipalEsElBueno() {
    assertEquals(equipo.mejorHeroeSegun({ _.statPrincipal.getOrElse(0) }), heroeBueno)
  }
  
  @Test
  def seAgregaMiembroNuevo() {
    val otro = new Heroe(Stat(5,5,5,5))
    equipo.obtenerMiembro(otro)
   
    assert(equipo.integrantes.contains(otro))
  }
  
  @Test
  def sePuedeReemplazarMiembro() {
    val otro = new Heroe(Stat(5,5,5,5))
    equipo.reemplazarMiembro(heroeMalo,otro)
   
    assert(equipo.integrantes.contains(otro))
    assert(!equipo.integrantes.contains(heroeMalo))
  }
  
  @Test
  def elLiderEsElHeroeBueno() {
    
    assertEquals(equipo.lider,Some(heroeBueno))
  }
  
  @Test
  def noHayLiderClaroEnAlgunosCasos() {
    val otro = heroeBueno.copy()
    equipo.obtenerMiembro(otro)
    
    assertEquals(equipo.lider,None)
  }
}
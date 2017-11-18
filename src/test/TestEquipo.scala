package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main._
import junit.framework.AssertionFailedError

class TestEquipo {
  val statsBuenos = new Stat(20,20,20,20)
  val statsMalos = new Stat(1,1,1,1)
  
  val listaItems = Some(List(cascoVikingo,armaduraEleganteSport))
  
  val heroeBueno = new Heroe(statsBuenos,Some(Ladron),listaItems)
  val heroeMalo = new Heroe(statsMalos)
  
  var equipo : Equipo = null
  
  
  @Before
  def setUp() {
    equipo = new Equipo(integrantes = Some(List(heroeBueno,heroeMalo)))
  }

  @Test
  def mejorHeroeSegunStatPrincipalEsElBueno() {
    assertEquals(equipo.mejorHeroeSegun({_.statPrincipal.getOrElse(0)}),heroeBueno)
  }
  
  @Test
  def seAgregaMiembroNuevo() {
    val otro = new Heroe(new Stat(5,5,5,5))
    equipo.obtenerMiembro(otro)
   
    assert(equipo.integrantes.get.contains(otro))
  }
  
  @Test
  def sePuedeReemplazarMiembro() {
    val otro = new Heroe(new Stat(5,5,5,5))
    equipo.reemplazarMiembro(heroeMalo,otro)
   
    assert(equipo.integrantes.get.contains(otro))
    assert(!equipo.integrantes.get.contains(heroeMalo))
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
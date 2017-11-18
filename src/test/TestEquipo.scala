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
  
}
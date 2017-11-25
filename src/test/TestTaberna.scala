package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import main._
import main.CriteriosDeMision._
import junit.framework.AssertionFailedError

class TestTaberna{
  
  val heroe = Heroe(Stat(10, 10, 10, 10), None, List())
  val mago = Heroe(Stat(15, 15, 15, 15), Some(Mago), List())
  val guerrero = Heroe(Stat(40, 10, 10, 10), Some(Guerrero), List())
  
  val integrantes = List(guerrero)
  val equipo = Equipo("mairaRuano", integrantes, 0)
  
  val tabernaAbrePuertas = new Taberna(List(abrirPuerta))
  val tabernaColoPt = new Taberna(List(pegarleAlColo))
  val tabernaRobarTalisman = new Taberna(List(robarCosas))

  @Before
  def setUp() {
  }
  
  @Test
  def eligeBienMisionSimple() {
    assertEquals(tabernaAbrePuertas.elegirMejorMisionPara(equipo)(porMasOro), Some(abrirPuerta))
  }
  
  @Test
  def noHayMisionSiFalla() {
    assertEquals(tabernaRobarTalisman.elegirMejorMisionPara(equipo)(porMasOro), None)
  }
  
}
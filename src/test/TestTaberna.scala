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
  
  val integrantes = List(mago, guerrero)
  val equipo = Equipo("mairaRuano", integrantes, 0)

  @Before
  def setUp() {
  }
  
  @Test
  def asd() {
    assertEquals(Taberna.elegirMejorMisionPara(equipo)(porMasOro), pegarleAlColo)
  }
  
}
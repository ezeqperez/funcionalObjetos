package test
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main._
import junit.framework.AssertionFailedError

class TestTarea {
  var heroe : Heroe = null
  var lider : Heroe = null
  var mago : Heroe = null
  var ladri : Heroe = null
  var equipo : Equipo = null
  var integrantes : List[Heroe] = null
  
  @Before
  def setUp(){
    heroe = Heroe(Stat(10,10,10,10),None,List(),List(pelearContraMonstruo))
    ladri = Heroe(Stat(15,15,15,15),Some(Ladron),List(),List())
    mago = Heroe(Stat(15,15,15,15),Some(Mago),List(),List())
    lider = Heroe(Stat(40,10,10,10),Some(Guerrero),List(),List())
    
    integrantes = List(heroe, lider)
    equipo = Equipo ("los power rangers", integrantes, 0)
  }
  
  @Test
  def testModificarStat(){
     assertEquals(Stat(1,10,10,10),heroe.stats)
  }
  
  @Test
  def testFacilidad(){
     assertEquals(20,pelearContraMonstruo.facilidad(equipo, heroe))
  }
  
   @Test
  def testFacilidadDos(){
     integrantes = mago :: integrantes
     assert(integrantes.contains(mago))
     assertEquals(10,pelearContraMonstruo.facilidad(Equipo ("equipo auxiliar con mago", integrantes, 0), heroe))
  }
  
  @Test
  def testFacilidadTres(){
     integrantes = ladri :: integrantes
     assert(integrantes.contains(ladri))
     assertEquals(20,forzarPuerta.facilidad(Equipo ("equipo auxiliar con ladron", integrantes, 0),heroe))
  }
  
  @Test
  def testFacilidadCuatro(){
     integrantes = ladri :: integrantes
     assert(integrantes.contains(ladri))
     assertEquals(10,forzarPuerta.facilidad(equipo,heroe))
  }
}
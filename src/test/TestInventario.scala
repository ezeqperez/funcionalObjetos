package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main._
import junit.framework.AssertionFailedError

class TestInventario {
    var heroe : Heroe = null
    var listaItems : List[Item] = null
    
  @Before
  def setUp() {
    listaItems = List(cascoVikingo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,talismanMinimalismo,vinchaBufalo,palitoMagico,escudoAntiRobo,espadaDeLaVida)
    heroe = new Heroe(Stat(10,10,10,10),None,listaItems,List())
  }

  @Test
  def testeoDeInventario() {
    assertEquals(List(cascoVikingo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,talismanMinimalismo,vinchaBufalo,palitoMagico,escudoAntiRobo,espadaDeLaVida), heroe.inventario.items)
  }

}
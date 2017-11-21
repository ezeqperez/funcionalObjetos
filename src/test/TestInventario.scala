package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main._
import junit.framework.AssertionFailedError

class TestInventario {
  
    var heroe : Heroe = null
    var heroeBueno : Heroe = null
    var listaItems : List[Item] = null
    var listaItemsBuena : List[Item] = null
    
  @Before
  def setUp() {
    listaItems = List(cascoVikingo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,
        talismanMinimalismo,vinchaBufalo,palitoMagico,escudoAntiRobo,espadaDeLaVida)
    heroe = new Heroe(Stat(10,10,10,10),None,listaItems,List())
    
    listaItemsBuena = List(cascoVikingo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,
        talismanMinimalismo)
    heroeBueno = new Heroe(Stat(10,10,10,10),None,listaItemsBuena,List())
  }

  @Test
  def testeoDeInventario() {
    assertEquals(List(), heroe.inventario.items)
  }

  @Test
  def testeoDeInventarioBueno() {
    
    assertEquals(List(cascoVikingo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,
        talismanMinimalismo, espadaDeLaVida), heroeBueno.equipar(espadaDeLaVida).inventario.items)
  }
  
  
}
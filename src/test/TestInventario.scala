package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main._
import junit.framework.AssertionFailedError

class TestInventario {
    var heroe : Heroe = null
    var listaItems : Option[List[Item]] = null
    
  @Before
  def setUp() {
    
    listaItems = Some(List(cascoVikingo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,talismanMinimalismo,vinchaBufalo,palitoMagico,escudoAntiRobo,espadaDeLaVida))
    heroe = new Heroe(new Stat(10,10,10,10),None,listaItems)
  }

  @Test
  def testeoDeTipos() {
    assertEquals(true, heroe.inventario.mismoTipoQue(cascoVikingo)(vinchaBufalo))
  

  }
  
  @Test
  def testeoDeInventario() {
    
    assertEquals(List(vinchaBufalo,armaduraEleganteSport,talismanDedicacion,talismanMaldito,talismanMinimalismo,espadaDeLaVida), heroe.inventario.itemsEquipados)

  }

}
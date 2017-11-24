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
    val miLista = listaItemsBuena.+:(espadaDeLaVida)
    
    assert(heroe.inventario.items.forall(miLista.contains(_)))
  }

  @Test
  def testeoDeInventarioBueno() {
    val miLista = listaItemsBuena.+:(espadaDeLaVida)
    
    assert(heroeBueno.equipar(espadaDeLaVida).inventario.items.forall(miLista.contains(_)))
  }
  
  @Test
  def espadaDescartadaSeAgregaDeNuevo() {
    val inv = Inventario(listaItemsBuena.+:(espadaDeLaVida),new Heroe())
    val miLista = listaItemsBuena.+:(escudoAntiRobo)
    
    assert(inv.agregarItem(escudoAntiRobo).items.forall(miLista.contains(_)))
  }
}
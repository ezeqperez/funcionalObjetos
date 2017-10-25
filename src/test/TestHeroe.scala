package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main.heroe.Heroe
import main.inventario.Inventario
import main.inventario.items.Item
import main.inventario.items.Casco
import main.trabajo.Trabajo
import main.trabajo.Guerrero
import main.trabajo.Mago
import stats.Inteligencia
import stats.Fuerza

class TestHeroe {
  var heroe:Heroe = null
  var inventario:Inventario = null
  var trabajo:Trabajo = null
  
  @Before
  def setUp(){
    heroe = new Heroe(7,4,3,2)
    inventario = new Inventario
    trabajo = new Guerrero
  }
  
  @Test
  def creaHeroe(){
    assertEquals(7, heroe.hp)
    assertEquals(4, heroe.fuerza)
    assertEquals(3, heroe.velocidad)
    assertEquals(2, heroe.inteligencia)
    assertEquals(null, heroe.trabajo)
    assertEquals(null, heroe.inventario)
    
    heroe.trabajo = trabajo
    assertEquals(trabajo, heroe.trabajo)
  }
  
  @Test
  def heroeConInventario(){
    var casco = new Casco
    heroe.inventario = inventario
    inventario.casco = casco        
    assertEquals(casco, heroe.inventario.casco)
    assertEquals(null, heroe.inventario.cuerpo)
    
  }
  
  @Test
 //TODO: Hay que armar el test como corresponde
  def seLeAsignaTrabajoAUnHeroe(){
    heroe.trabajo = trabajo
   // assertEquals(new Fuerza, heroe.trabajo.statPrincipal)
    
    heroe.trabajo = new Mago
   // assertEquals(new Inteligencia, heroe.trabajo.statPrincipal)
    
    
  }
  
}
package test

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import main.heroe.Heroe
import main.inventario.Inventario
import main.inventario.items.Item
import main.inventario.items.Casco

class TestHeroe {
  var heroe:Heroe = null
  var inventario:Inventario = null
  
  @Before
  def setUp(){
    heroe = new Heroe(7,4,3,2)
    inventario = new Inventario
    heroe.inventario = inventario
    
  }
  
  //Al cuete ahora
  @Test
  def creaHeroe(){
    assertEquals(7, heroe.hp)
    assertEquals(4, heroe.fuerza)
    assertEquals(3, heroe.velocidad)
    assertEquals(2, heroe.inteligencia)
    assertEquals(null, heroe.trabajo)
    assertEquals(inventario, heroe.inventario)
  }
  
  @Test
  def heroeConInventario(){
    var casco = new Casco
    inventario.casco = casco        
    assertEquals(casco, heroe.inventario.casco)
    assertEquals(null, heroe.inventario.cuerpo)
    
  }
  
}
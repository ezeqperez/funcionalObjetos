package main


case class Tarea() extends ModificacionDeStats{

def facilidad(eq: Equipo, heroe: Heroe) = 1
def efectoPara(heroe: Heroe, stat: Stat) = new Stat()
/* - “pelear contra monstruo” reduce la vida de cualquier héroe con fuerza <20
  					tiene una facilidad de 10 para cualquier héroe o 20 si el líder del equipo es un guerrero
   - “forzar puerta” no le hace nada a los magos ni a los ladrones,
  		pero sube la fuerza de todos los demás en 1 y baja en 5 su hp
  					tiene facilidad igual a la inteligencia del héroe + 10 por cada ladrón en su equipo
   - “robar talismán” le agrega un talismán al héroe.
   				tiene facilidad igual a la velocidad del héroe, pero no puede ser hecho por equipos cuyo líder no sea un ladrón
 */
}

class NoPuedeRealizarTareaException extends RuntimeException

object pelearContraMonstruo extends Tarea {
  
  override def efectoPara(heroe: Heroe, stat: Stat): Stat = {
    stat match{
      case _ if (stat.fuerza < 20) => return stat.copy()
      case _ => return cambiarHpEn(-10)(stat)
    }
   }
  override def facilidad(eq: Equipo, heroe: Heroe) = {
     eq.lider().getOrElse(None) match{
       
       case Some(h:Heroe) if(h.trabajo == Some(Guerrero)) => 20
       case Some(h:Heroe) => 10
       case _ => throw new NoPuedeRealizarTareaException
     }
  }
}
  
 object forzarPuerta extends Tarea{
   
   override def efectoPara(heroe: Heroe, stat: Stat): Stat = {
     
     heroe.trabajo match{
       case Some(Mago) => return stat.copy()
       case Some(Ladron) => return stat.copy()
       case _ => return cambiarHpEn(-5)(cambiarFuerzaEn(1) (stat))
     }
   }
   
   override def facilidad(eq: Equipo, heroe: Heroe): Int = heroe.getInteligencia + eq.cantidadDeLadrones() * 10  
}
 
 
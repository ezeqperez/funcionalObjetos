package main


case class Tarea() extends ModificacionDeStats{

def facilidad(eq: Equipo, heroe: Heroe) = 1
def efectoPara(heroe: Heroe, stat: Stat) = Stat()

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
     eq.lider match{
       
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
   
   override def facilidad(eq: Equipo, heroe: Heroe): Int = heroe.getInteligencia + eq.cantidadDe(Ladron) * 10  
}
 
 
package main


trait Tarea extends ModificacionDeStats{

  def facilidad(eq: Equipo, heroe: Heroe) : Int
  def ejecutadaPor(heroe: Heroe) : Heroe
}


object pelearContraMonstruo extends Tarea {
  
  def ejecutadaPor(heroe: Heroe): Heroe = heroe
  
//  def ejecutadaPor(heroe: Heroe): Heroe = {
//    stat match{
//      case _ if (stat.fuerza < 20) => return stat.copy()
//      case _ => return cambiarHpEn(-10)(stat)
//    }
//  } aca el heroe deberia tener un statFinal, sino imposible trackear su progreso
  
  def facilidad(eq: Equipo, heroe: Heroe) = {
     eq.lider match{
       
       case Some(h:Heroe) if(h.trabajo == Some(Guerrero)) => 20
       case Some(h:Heroe) => 10
     }
  }
}
  
 object forzarPuerta extends Tarea{
   
   override def ejecutadaPor(heroe: Heroe, stat: Stat): Stat = {
     
     heroe.trabajo match{
       case Some(Mago) => return stat.copy()
       case Some(Ladron) => return stat.copy()
       case _ => return cambiarHpEn(-5)(cambiarFuerzaEn(1) (stat))
     }
   }
   
   override def facilidad(eq: Equipo, heroe: Heroe) = heroe.getInteligencia + eq.cantidadDe(Ladron) * 10  
}
 
 
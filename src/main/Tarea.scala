package main


trait Tarea extends ModificacionDeStats{

  def facilidad(eq: Equipo, heroe: Heroe) : Int
  def ejecutadaPor(heroe: Heroe): Heroe = heroe.copy(tareasRealizadas = heroe.tareasRealizadas.+:(this))
  def efectoPara(heroe: Heroe)(stat: Stat): Stat
}


object pelearContraMonstruo extends Tarea {
  
  def efectoPara(heroe: Heroe)(stat: Stat): Stat = {
    if (stat.fuerza < 20)
      cambiarHpEn(-10)(stat)
    else
      stat
  }
  
  def facilidad(eq: Equipo, heroe: Heroe) = {
     eq.lider match{
       case Some(h) if(h.trabajo == Some(Guerrero)) => 20
       case Some(_) => 10
       case _ => 0
     }
  }
}
  
 object forzarPuerta extends Tarea{
   
   def efectoPara(heroe: Heroe)(stat: Stat): Stat = {
     heroe.trabajo match{
       case Some(Mago) => stat
       case Some(Ladron) => stat
       case _ => (cambiarHpEn(-5)_ compose cambiarFuerzaEn(1)_)(stat)
     }
   }
   
   override def facilidad(eq: Equipo, heroe: Heroe) = heroe.getInteligencia + eq.cantidadDe(Ladron) * 10  
}
 
 
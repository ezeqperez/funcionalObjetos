package main

class Mision {
  var tareas: List[Tarea] = Nil
  val recompensas: Int = 0 //esto debe ser una lista de cosas (heroe, item, aumentarStats - cosas en comun con equiparItem)
}

class Tarea {
  var facilidad: Int = 0
  /* - “pelear contra monstruo” reduce la vida de cualquier héroe con fuerza <20
    					tiene una facilidad de 10 para cualquier héroe o 20 si el líder del equipo es un guerrero
     - “forzar puerta” no le hace nada a los magos ni a los ladrones,
    		pero sube la fuerza de todos los demás en 1 y baja en 5 su hp
    					tiene facilidad igual a la inteligencia del héroe + 10 por cada ladrón en su equipo
     - “robar talismán” le agrega un talismán al héroe.
     				tiene facilidad igual a la velocidad del héroe, pero no puede ser hecho por equipos cuyo líder no sea un ladrón
   */
}
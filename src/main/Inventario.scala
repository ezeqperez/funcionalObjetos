package main

trait Item {
  def nuevoInventario(viejo: List[Item]): List[Item] = {
    viejo.filterNot(item => sosMiTipo(item)).::(this)
  }

  def sosMiTipo(item: Item): Boolean = {
    return item.getClass.eq(this.getClass)
  }

  def sosTipoMano(item: Item): Boolean = {
    item match {
      case ItemMano()       => true
      case ItemAmbasManos() => true
      case _                => false
    }
  }

  def cumpleRestricciones(heroe: Heroe): Boolean = {
    return true //TODO podria tener una coleccion de funciones a ser aplicada, pero no se..
  }
}

case class ItemCabeza() extends Item
case class ItemTorso() extends Item
case class ItemMano() extends Item {
  override def nuevoInventario(viejo: List[Item]): List[Item] = {
    def armaDeMano : List[Item] = this.getOtraMano(viejo).toList
    viejo.filterNot(item => sosTipoMano(item)).::(this)
    //filtrar item de una mano y agregarlo a lo retornado
  }

  def getOtraMano(viejo: List[Item]): Option[Item] = {
    Option(viejo.filter(item => sosMiTipo(item)).head)
  }
}
case class ItemAmbasManos() extends Item {
  override def nuevoInventario(viejo: List[Item]): List[Item] = {
    viejo.filterNot(item => sosTipoMano(item)).::(this)
  }
}

case class ItemTalisman() extends Item {
  override def nuevoInventario(viejo: List[Item]): List[Item] = {
    return viejo.::(this)
  }
}

/*
Casco Vikingo: +10 hp, sólo lo pueden usar héroes con fuerza base > 30. Va en la cabeza.

Palito mágico: +20 inteligencia, sólo lo pueden usar magos (o ladrones con más de 30 de inteligencia base). Una mano.

Armadura Elegante-Sport: +30 velocidad, -30 hp. Armadura.

Arco Viejo: +2 fuerza. Ocupa las dos manos.

Escudo Anti-Robo: +20 hp. No pueden equiparlo los ladrones ni nadie con menos de 20 de fuerza base. Una mano.

Talismán de Dedicación: Todos los stats se incrementan 10% del valor del stat principal del trabajo.

Talismán del Minimalismo: +50 hp. -10 hp por cada otro ítem equipado.

Vincha del búfalo de agua: Si el héroe tiene más fuerza que inteligencia, +30 a la inteligencia; de lo contrario +10 a todos los stats menos la inteligencia. Sólo lo pueden equipar los héroes sin trabajo. Sombrero.
*/
//Talismán maldito: Todos los stats son 1.
object TalismanMaldito extends ItemTalisman {
  def equipar(heroe: Heroe): Heroe = {
    return heroe.copy(inventario = nuevoInventario(heroe.inventario), hpBase = 1, fuerzaBase = 1, inteligenciaBase = 1, velocidadBase = 1)
  }
}

//Espada de la Vida: Hace que la fuerza del héroe sea igual a su hp.
object EspadaDeLaVida extends ItemMano {
  def equipar(heroe: Heroe): Heroe = {
    return heroe.copy(hpBase = heroe.hpBase, fuerzaBase = heroe.hpBase, velocidadBase = heroe.velocidadBase, inteligenciaBase = heroe.inteligenciaBase, inventario = nuevoInventario(heroe.inventario))
  }
}

package org.example.monde

import org.example.Aquamy
import org.example.Bugsyface
import org.example.Flamkip
import org.example.Galum
import org.example.Laoumi
import org.example.Springleaf
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import kotlin.random.Random

class Zone (
    var id: Int,
    var nom: String = "",
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf<EspeceMonstre>(),
    var zoneSuivante: Zone?, //Zone OU Null
    var zonePrecedante: Zone?,


    //TODO rencontreMonstre()
    ){

    fun genereMonstre(){
        var especes = listOf(Springleaf, Flamkip, Aquamy, Laoumi, Bugsyface, Galum)
        var espece = especes.random()
        var id = Random.nextInt(1,100)

        var monstregenere = IndividuMonstre()
    }
}
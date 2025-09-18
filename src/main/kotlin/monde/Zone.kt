package org.example.monde

import org.example.monstre.EspeceMonstre

class Zone (
    var id: Int,
    var nom: String = "",
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf<EspeceMonstre>(),
    var zoneSuivante: Zone?, //Zone OU Null
    var zonePrecedante: Zone?,

    //TODO generateMonstre()
    //TODO rencontreMonstre()
    ){

}
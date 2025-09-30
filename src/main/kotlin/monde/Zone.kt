package org.example.monde

import org.example.Aquamy
import org.example.Bugsyface
import org.example.Flamkip
import org.example.Galum
import org.example.Laoumi
import org.example.Springleaf
import org.example.jeu.CombatMonstre
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import kotlin.random.Random
import org.example.joueur


class Zone (
    var id: Int,
    var nom: String = "",
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf<EspeceMonstre>(),
    var zoneSuivante: Zone?, //Zone OU Null
    var zonePrecedante: Zone?,

    ){

    fun genereMonstre(): IndividuMonstre{

        var myEspece = especesMonstres.random()
        var myId = Random.nextInt(1,100)
        var myExpInit = expZone + ((-20 until 20).random()/100)*expZone


        var monstregenere = IndividuMonstre(id = myId,nom = myEspece.nom, espece = myEspece, entraineur = null, expInit = myExpInit.toDouble())

        return monstregenere
    }

    fun rencontreMonstre(){
        var monstreSauvage = genereMonstre()
        var premierPokemon: IndividuMonstre? = null
        for(monstre in joueur.equipeMonstre){
            if(monstre.pv>0) premierPokemon = monstre
        }
        var monCombat = CombatMonstre(monstreJoueur = premierPokemon!!, monstreSauvage = monstreSauvage)
        monCombat.lanceCombat()
    }

}
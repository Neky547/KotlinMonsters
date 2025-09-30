package org.example.jeu

import org.example.Aquamy
import org.example.Flamkip
import org.example.Springleaf
import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre

class Partie (
    var id: Int,
    var joueur: Entraineur?,
    var zone: Zone
){
    fun choixStarter(){
        val monstre1 = IndividuMonstre(1, "springleaf", Springleaf, null, 1500.0)
        val monstre2 = IndividuMonstre(2, "flamkip", Flamkip, null, 1500.0)
        val monstre3 = IndividuMonstre(3, "aquamy", Aquamy, null, 1500.0)
        var starter: IndividuMonstre? = null

        //Afficher les détails des monstres
        monstre1.afficheDetail()
        monstre2.afficheDetail()
        monstre3.afficheDetail()

        //Afficher menu de choix
        println("Choisissez votre monstre :\n 1.Springleaf\n 2.Flamkip\n 3.Aquamy")
        var choixSelection = readln().toInt()

        if(choixSelection !in 1..3) choixStarter()
        if(choixSelection == 1) {starter = monstre1}
        else if(choixSelection == 2) {starter = monstre2}
        else { var starter = monstre3}

        //Permettre à l'utilisateur de renommer le starter
        starter!!.renommer()

        //Ajouter le monstre à l'équipe du joueur
        joueur!!.equipeMonstre.add(starter)
        starter.entraineur = joueur

    }

}
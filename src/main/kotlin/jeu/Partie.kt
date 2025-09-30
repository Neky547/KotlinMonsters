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

        //Vérifie si le numéro de choix est bien valide
        if(choixSelection !in 1..3) choixStarter()

        if(choixSelection == 1) {starter = monstre1}
        else if(choixSelection == 2) {starter = monstre2}
        else {starter = monstre3}

        //Permettre à l'utilisateur de renommer le starter
        starter!!.renommer()

        //Ajouter le monstre à l'équipe du joueur
        joueur!!.equipeMonstre.add(starter)
        starter.entraineur = joueur

    }

    //Permettre d'échanger les positions de deux monstres dans l'equipe
    fun modifierOrdreEquipe(){
        println("Entrez la position du monstre que vous voulez déplacer : ")
        var position1 = readln().toInt()

        println("Entrez la nouvelle position du monstre : ")
        var position2 = readln().toInt()

        var firstPokemon = joueur!!.equipeMonstre[position1]
        var secondPokemon = joueur!!.equipeMonstre[position2]

        joueur!!.equipeMonstre[position1] = secondPokemon
        joueur!!.equipeMonstre[position2] = firstPokemon

        println(joueur!!.equipeMonstre)
    }

    /**
     * Affiche les détails des monstres
     * Demande si l'utilisateur veut modifier l'ordre*/
    fun examinerEquipe(){
        for(monstre in joueur!!.equipeMonstre){
            monstre.afficheDetail()
            modifierOrdreEquipe()
        }
    }
}
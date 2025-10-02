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
    fun examineEquipe(){
        for(monstre in joueur!!.equipeMonstre){
            println("Pokemon : $monstre\n" +
                    "Numéro : ${joueur!!.equipeMonstre.indexOf(monstre)}")
        }
        println("Si vous voulez retourner au menu principal : tapez q\n" +
                "Si vous voulez modifier l'ordre des monstres : tapez m\n"+
                "Tapez le numéro du monstre pour voir les détails :")
        var entree = readln()
        if(entree.lowercase() == "q") jouer()
        else if(entree.lowercase() == "m") {modifierOrdreEquipe()}
        else if(0 < entree.toInt() && entree.toInt()< joueur!!.equipeMonstre.size){
            var monstre = joueur!!.equipeMonstre[entree.toInt()]
            monstre.espece.afficheArt()
            monstre.afficheDetail()
        } else {
            println("Entrée invalide")
            examineEquipe()
        }
    }


/**
 * Méthode Jouer qui permet d'indiquer au joueur la zone où il se trouve
 * Lui indique les actions qu'il peut exécuter en fonction de la zone*/

    fun jouer() {
        var continuer = true

        while (continuer) {
            // Afficher la zone actuelle
            println("=== Vous êtes dans : ${zone.nom} ===")
            println("Zone d'expérience : ${zone.expZone}")
            println("Monstres possibles : ${zone.especesMonstres.joinToString { it.nom }}")

            // Afficher les actions possibles
            println("\nActions possibles :")
            println("1 - Rencontrer un monstre sauvage")
            println("2 - Examiner l'équipe de monstres")
            println("0 - Quitter le jeu")

            // Lire le choix du joueur
            print("Votre choix : ")
            val choix = readLine()?.toIntOrNull()

            when (choix) {
                1 -> {
                    println("\n=== Rencontre d'un monstre sauvage ===")
                    // Appeler la méthode rencontreMonstre() de la zone
                    // Note: Vous devrez implémenter cette méthode dans Zone
                    zone.rencontreMonstre()
                }
                2 -> {
                    println("\n=== Examen de l'équipe ===")
                    examineEquipe()
                }
                3 -> {
                    if (zone.zoneSuivante != null) {
                        println("\nDéplacement vers la zone suivante...")
                        zone = zone.zoneSuivante!!
                        println("Vous arrivez dans : ${zone.nom}")
                    } else {
                        println("\nErreur : Aucune zone suivante disponible !")
                    }
                }
                4 -> {
                    if (zone.zonePrecedante != null) {
                        println("\nDéplacement vers la zone précédente...")
                        zone = zone.zonePrecedante!!
                        println("Vous arrivez dans : ${zone.nom}")
                    } else {
                        println("\nErreur : Aucune zone précédente disponible !")
                    }
                }
                0 -> {
                    println("\nAu revoir !")
                    continuer = false
                }
                else -> {
                    println("\nChoix invalide ! Veuillez choisir une option valide.")
                }
            }

            println() // Ligne vide pour la lisibilité
        }
    examineEquipe()
    }
}
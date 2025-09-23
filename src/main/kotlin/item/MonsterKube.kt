package org.example.item

import org.example.joueur
import org.example.monstre.IndividuMonstre
import kotlin.uuid.Uuid.Companion.random

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
) : Item(id, nom, description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous lancez le Monstre Kube!")
        if(cible.entraineur != null){
            println("Le monstre ne peut pas être capturé.")
            return false
        } else {
            var nbAleatoire = (0 until 100).random()
            if(chanceCapture <= nbAleatoire){
                println("Presque! Le Kube n'a pas pu capturer le monstre!")
            } else {
                println("Le monstre est capturé !")
                println("Entrez un nouveau nom : ")
                var nouveauNom = readln()
                if(nouveauNom.isNotEmpty()) cible.nom = nouveauNom
                if(joueur.equipeMonstre.size>=6){
                    joueur.boiteMonstre.add(cible)
                } else {
                    joueur.equipeMonstre.add(cible)
                }
                cible.entraineur = joueur
                return true
            }
        }

    }

}
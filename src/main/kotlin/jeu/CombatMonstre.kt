package org.example.jeu

import org.example.joueur
import org.example.monstre.IndividuMonstre

class CombatMonstre(
    val monstreJoueur: IndividuMonstre,
    val monstreSauvage: IndividuMonstre
    ) {
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean{
        var gagner = true
        for (monstreJoueur in joueur.equipeMonstre){
            if(0 < monstreJoueur.pv){
                gagner = false
            }
        }
        return gagner
    }


    fun joueurGagne(): Boolean{
        if(monstreSauvage.pv <= 0){
            println("${joueur.nom} a gagné !")
            var gainExp = monstreSauvage.exp*0.20
            monstreJoueur.exp += gainExp
            println("${monstreJoueur.nom} gagne $gainExp exp")
            return true
        } else {
            if(monstreSauvage.entraineur == joueur){
                println("${monstreSauvage.nom} a été capturé !")
                return true
            } else {
                return false
            }
        }
    }

    fun actionAdversaire(){
        if(0 < monstreSauvage.pv ) monstreSauvage.attaquer(monstreJoueur)
    }

    fun actionJoueur(): Boolean{

    }

}
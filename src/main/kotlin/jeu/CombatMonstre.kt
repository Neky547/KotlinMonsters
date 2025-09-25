package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.item.Utilisable
import org.example.joueur
import org.example.monstre.IndividuMonstre

class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    var monstreSauvage: IndividuMonstre
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
        var perdu = false
        for (monstre in joueur.equipeMonstre){
            if(monstre.pv <= 0){
                perdu = true
            }
        }
        return perdu
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
            } else return false
        }
    }

    fun actionAdversaire(){
        if(0 < monstreSauvage.pv ) monstreSauvage.attaquer(monstreJoueur)
    }

    fun actionJoueur(): Boolean{
        if(gameOver()==true){
            return false
        } else {
            println("Entrez le numéro d'action : 1. Attaquer\n 2. Utiliser un objet\n 3. Changer de monstre")
            var choixAction = readln()
            if(choixAction=="1") monstreJoueur.attaquer(monstreSauvage)

            if(choixAction=="2") {
                println(joueur.sacAItems)
                var indexChoix = readln().toInt()
                var objetChoisi = joueur.sacAItems[indexChoix]
                if(objetChoisi is Utilisable){
                    var captureRéussie = objetChoisi.utiliser(monstreSauvage)
                    if(captureRéussie) return false else return false
                } else {
                    println("Objet non utilisable")
                    return true
                }
            }

            if(choixAction=="3"){
                for (monstre in joueur.equipeMonstre){
                    if (0<monstre.pv) println(monstre)
                }
                var indexChoix = readln().toInt()
                var choixMonstre = joueur.equipeMonstre[indexChoix]
                if(choixMonstre.pv<=0){
                    println("Impossible, ce monstre est K.O")
                } else {
                    println("$choixMonstre remplace $monstreJoueur")
                    monstreJoueur = choixMonstre
                }
            }
        }
        return true
    }

    fun afficherCombat(){
        println("======== Début Round : $round ========\n" +
        "Niveau: ${monstreSauvage.niveau}\n PV: ${monstreSauvage.pv}/${monstreSauvage.pvMax}\n"+
        monstreSauvage.espece.afficheArt(true)+
        monstreJoueur.espece.afficheArt(false)+
        "Niveau: ${monstreJoueur.niveau}\n PV: ${monstreJoueur.pv}/${monstreJoueur.pvMax}")
    }

    fun jouer(){
        afficherCombat()
        if(monstreJoueur.vitesse >= monstreSauvage.vitesse){
            if(actionJoueur() == false)  return
            actionAdversaire()
        } else {
            actionAdversaire()
            if (gameOver() == false){
                actionJoueur()
            }
            return
        }
    }

    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV
     * de tous ses monstres.
     */
    fun lanceCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }


}
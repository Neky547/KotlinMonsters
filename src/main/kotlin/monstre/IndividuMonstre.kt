package org.example.monstre

import kotlin.math.round
import kotlin.math.pow
import kotlin.random.Random
import org.example.dresseur.Entraineur

class IndividuMonstre(
    var id: Int,
    var nom: String= "",
    var espece: EspeceMonstre,
    var entraineur: Entraineur?,
    expInit: Double,

    ) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + (Random.nextInt(-2, 3))
    var defense: Int = espece.baseDefense + (Random.nextInt(-2, 3))
    var vitesse: Int = espece.baseVitesse + (Random.nextInt(-2, 3))
    var attaqueSpe: Int = espece.baseAttaqueSpe + (Random.nextInt(-2, 3))
    var defenseSpe: Int = espece.baseDefenseSpe + (Random.nextInt(-2, 3))
    var pvMax: Int = espece.basePv + (Random.nextInt(-5, 6))
    var potentiel: Double = Random.nextDouble(0.5, 3.0)

    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field=nouveauPv.coerceIn(0, pvMax)
        }

    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double{
        var palier = 100*(niveau-1).toDouble().pow(2.0)
        return palier
    }
    fun levelUp(){
        attaque = (round(attaque*potentiel)).toInt()+ (Random.nextInt(-2,3))
        defense = (round(defense*potentiel)).toInt()+ (Random.nextInt(-2,3))
        vitesse = (round(vitesse*potentiel)).toInt()+ (Random.nextInt(-2,3))
        attaqueSpe = (round(attaqueSpe*potentiel)).toInt()+ (Random.nextInt(-2,3))
        defenseSpe = (round(defenseSpe*potentiel)).toInt()+ (Random.nextInt(-2,3))
        pvMax = (round(pvMax*potentiel)).toInt()+ (Random.nextInt(-5,6))
    }


    /**
     * Getter et setter de exp : points d'expérience
    * Lorsque l'exp atteind un palier, on appelle la méthode levelUp()
    * */
    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            var estNiveau1 = (niveau == 1)
            if(niveau!=1) estNiveau1=false
            while(palierExp(niveau)<=field){

                levelUp()

                if(!estNiveau1){
                    println("Le monstre $nom est maintenant niveau $niveau!")
                }
            }
        }

    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up

    }

}
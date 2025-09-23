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
        niveau+=1
        attaque += (round(this.espece.modAttaque*potentiel)).toInt()+ (Random.nextInt(-2,3))
        defense += (round(this.espece.modDefense*potentiel)).toInt()+ (Random.nextInt(-2,3))
        vitesse += (round(this.espece.modVitesse*potentiel)).toInt()+ (Random.nextInt(-2,3))
        attaqueSpe += (round(this.espece.modAttaqueSpe*potentiel)).toInt()+ (Random.nextInt(-2,3))
        defenseSpe += (round(this.espece.modDefenseSpe*potentiel)).toInt()+ (Random.nextInt(-2,3))
        pvMax += (round(this.espece.modPv*potentiel)).toInt()+ (Random.nextInt(-5,6))
    }


    /**
     * Getter et setter de exp : points d'expérience
    * Lorsque l'exp atteind un palier, on appelle la méthode levelUp()
    * */
    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value

            // Vérification du niveau 1
            var estNiveau1 = (niveau == 1)

            // Tant que l’XP atteint le palier du niveau
            while (field >= palierExp(niveau)) {
                levelUp()

                // Affichage uniquement si ce n’est pas le niveau 1
                if (!estNiveau1) {
                    println("Le monstre $nom est maintenant niveau $niveau !")
                }
            }
        }



    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }

    //Méthode attaquer
    /**
     * Attaque un autre [IndividuMonstre] et inflige des dégâts.
     *
     * Les dégâts sont calculés de manière très simple pour le moment :
     * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
     *
     * @param cible Monstre cible de l'attaque.
     */
    fun attaquer(cible: IndividuMonstre){
        var degatBrut = this.attaque
        var degatTotal = degatBrut - (this.defense/2)
        if(degatTotal<1) degatTotal = 1
        var pvAvant = cible.pv
        cible.pv -= degatTotal
        var pvApres = cible.pv
        println("$nom inflige ${pvAvant - pvApres} dégâts à ${cible.nom} ")

    }

    /**
     * Demande au joueur de renommer le monstre.
     * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
     */

    fun renommer(){
        println("Renommer ${this.nom}? : ")
        var nouveauNom = readln()
        if(nouveauNom.isNotEmpty()) this.nom = nouveauNom

    }

    fun afficheDetail(){
        val details = "====================\n"+
        "Nom: ${this.nom}     Niveau: ${this.niveau}\n Exp: ${this.niveau}\n Pv: ${this.pv}\n"+
        "====================\n"+
        "Atq: ${this.attaque}     Def: ${this.defense}     Vitesse: ${this.vitesse}\n AtqSpe: ${this.attaqueSpe}     DefSpe: ${this.defenseSpe}\n"+
       "====================\n"

        println(details)
    }
}

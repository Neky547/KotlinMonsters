package org.example.monstre

import org.example.dresseur.Entraineur

class IndividuMonstre(
    var id: Int,
    var nom: String= "",
    var espece: MutableList<EspeceMonstre> = mutableListOf<EspeceMonstre>(),
    var entraineur: Entraineur?,
    expInit: Double,

    ) {
    var niveau: Int = 1

}
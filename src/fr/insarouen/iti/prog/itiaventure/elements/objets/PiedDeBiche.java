package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;


/**
 * Classe PiedDeBiche, heritant d'Objet. (Le pied de biche est un objet déplacable.)
 */
public class PiedDeBiche extends Objet {
    public PiedDeBiche(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
    }
    /**
     * methode abstraite de Objet implemente en methode concrete
     */
    public boolean estDeplacable(){
        return true;
    }
}
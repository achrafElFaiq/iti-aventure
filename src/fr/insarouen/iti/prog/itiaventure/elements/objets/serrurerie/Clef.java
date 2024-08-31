package fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public final class Clef extends Objet{

    /**
     * 
     */
    protected Clef(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);

    }

    /**
     * Méthode permettant de ...
     */
    public boolean estDeplacable(){
        return true;
    }
}
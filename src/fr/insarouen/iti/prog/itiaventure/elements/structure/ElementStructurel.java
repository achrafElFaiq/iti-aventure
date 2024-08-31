package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
/**
 * Classe abstraite ElementStructurel, heritant de Entite
 */
public abstract class ElementStructurel extends Entite {
    public ElementStructurel(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
    }

    
}
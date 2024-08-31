package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class ESTest extends ElementStructurel{

    public ESTest (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
    }

}
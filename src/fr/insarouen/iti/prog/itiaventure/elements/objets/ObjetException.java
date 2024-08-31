package fr.insarouen.iti.prog.itiaventure.elements.objets;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
/**
 * Classe ObjetException heritant de ITIAventureException
 */
public class ObjetException extends ITIAventureException{
    public ObjetException(String s){
        super(s);
    }

    public ObjetException(){
    }
}
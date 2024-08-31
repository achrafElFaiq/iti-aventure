package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
/**
 * Classe ESException heritant de ITIAventureException
 */
public class ESException extends ITIAventureException{
    public ESException(String s){
        super(s);
    }

    public ESException(){
    }
}
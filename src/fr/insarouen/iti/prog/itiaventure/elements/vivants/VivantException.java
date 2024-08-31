package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import fr.insarouen.iti.prog.itiaventure.ITIAventureException;


/**
 * Classe VivantException heritant de ITIAventureException
 */
public class VivantException extends ITIAventureException{
    public VivantException(String s){
        super(s);
    }

    public VivantException(){
    }
}
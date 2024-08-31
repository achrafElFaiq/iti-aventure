package fr.insarouen.iti.prog.itiaventure.elements.vivants;


/**
 * Classe CommandeImpossiblePourLeVivantException heritant VivantException
 */
public class CommandeImpossiblePourLeVivantException extends VivantException{
    public CommandeImpossiblePourLeVivantException(String s){
        super(s);
    }

    public CommandeImpossiblePourLeVivantException(){
    }
}
package fr.insarouen.iti.prog.itiaventure.elements.vivants;


/**
 * Classe ObjetNonPossedeParLeVivantException heritant VivantException
 */
public class ObjetNonPossedeParLeVivantException extends VivantException{
    public ObjetNonPossedeParLeVivantException(String s){
        super(s);
    }

    public ObjetNonPossedeParLeVivantException(){
    }
}
package fr.insarouen.iti.prog.itiaventure.elements.structure;
/**
 * Classe PorteInexistanteDansLaPieceException heritant PorteException
 */
public class PorteInexistanteDansLaPieceException extends PorteException{
    public PorteInexistanteDansLaPieceException(String s){
        super(s);
    }

    public PorteInexistanteDansLaPieceException(){
    }
}
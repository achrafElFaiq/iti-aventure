package fr.insarouen.iti.prog.itiaventure.elements.structure;


/**
 * Classe ObjetAbsentDeLaPieceException heritant de PieceException
 */
public class ObjetAbsentDeLaPieceException extends PieceException{
    public ObjetAbsentDeLaPieceException(String s){
        super(s);
    }

    public ObjetAbsentDeLaPieceException(){
    }
}
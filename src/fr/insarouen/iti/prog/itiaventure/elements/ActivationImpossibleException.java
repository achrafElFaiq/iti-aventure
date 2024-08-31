package fr.insarouen.iti.prog.itiaventure.elements;

/**
 * Classe ActivationImpossibleException heritant de ActivationException
 */
public class ActivationImpossibleException extends ActivationException{

    public ActivationImpossibleException(String s){
        super(s);
    }

    public ActivationImpossibleException(){}
}
package fr.insarouen.iti.prog.itiaventure.conditionsDeFin;
import fr.insarouen.iti.prog.itiaventure.ITIAventureException;

/**
 * Classe ActivationException heritant de ITIAventureException
 */
public class DifferentsEtatsDuJeuException extends ITIAventureException{

    public DifferentsEtatsDuJeuException(String s){
        super(s);
    }

    public DifferentsEtatsDuJeuException(){}
}
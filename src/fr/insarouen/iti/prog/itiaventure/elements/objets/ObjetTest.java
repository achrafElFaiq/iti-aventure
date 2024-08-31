package fr.insarouen.iti.prog.itiaventure.elements.objets;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class ObjetTest extends Objet{

    public ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }

    public boolean estDeplacable(){
        return false;
    }
}
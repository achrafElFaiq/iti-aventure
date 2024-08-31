package fr.insarouen.iti.prog.itiaventure.elements.objets;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
/**
 * Classe abstraite Objet, heritant de Entite. Lui ajoute la méthode abstraite estDéplacable()
 */
public abstract class Objet extends Entite{

    public Objet(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }
    /**
     * Methode abstraite pour determiner si l'objet est deplacable ou non
     * @return vrai si L'objet est deplacable et faux sinon
     */
    public abstract boolean estDeplacable();
}
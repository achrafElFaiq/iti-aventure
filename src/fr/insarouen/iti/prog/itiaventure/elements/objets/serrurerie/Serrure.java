package fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;

public class Serrure extends Objet implements Activable {
    private Clef clef = null;
    private Etat etat;

    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
        this.etat = Etat.DEVEROUILLE;
    }

    /**
     * Méthode permettant de savoir si la serrure peut être activer avec une clef
     * @param clef La clef à tester
     */
    public boolean activableAvec(Objet obj){
        return obj.equals(this.clef);
    }

    /**
     * Méthode permettant d'activer une serrure (renvoie automatiquement une erreur puisque nécessite une clef.)
     */
    public void activer() throws ActivationImpossibleException{
        throw new ActivationImpossibleException("Une serrure nécessite une clef pour être activée.");
    }

    /**
     * Méthode permettant de déverouiller / verrouiller une porte avec sa clef.
     */
    public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException{
        if (activableAvec(obj)){
             if(this.etat == Etat.VERROUILLE){
            this.etat = Etat.DEVEROUILLE;
            }
            else{
                this.etat = Etat.VERROUILLE;
            }
        }
        else {
            throw new ActivationImpossibleAvecObjetException(String.format("L'objet %s n'est pas la clef de la serrure.", obj.getNom()));
        }
    }

    /**
     * Méthode permettant de créer la clef de la porte.
     */
    public Clef creerClef() throws NomDEntiteDejaUtiliseDansLeMondeException{
        if (this.clef == null){
            this.clef = new Clef(String.format("clef%s", this.getNom()), this.getMonde());
        }
        return this.clef;
    }

    public boolean estDeplacable(){
        return false;
    }

    /**
     * Méthode permettant de connaître l'état de la porte.
     */
    public Etat getEtat(){
        return this.etat;
    }
}
package fr.insarouen.iti.prog.itiaventure.elements.objets;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;


public class Coffre extends Objet implements Activable{

    private Etat etat;

    public Coffre(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }

    public boolean activableAvec(Objet obj){
        return true;
    }

    public void activer(){
        if(this.etat == Etat.FERME){
            this.etat = Etat.OUVERT;
        }
        else{
            this.etat = Etat.FERME;
        }
    }

    public void activerAvec(Objet Obj){
        this.activer();
    }

    public boolean estDeplacable(){
        return true;
    }

    public Etat getEtat(){
        return this.etat;
    }

    /**
     * Méthode permettant d'obtenir le coffre en cdc.
     * @return  une cdc contenant le nom du coffre et le monde auquel il appartient .
     */
    public String toString(){
        return String.format("Le coffre %s  appartient au monde %s. ", this.getNom(),this.getMonde().getNom());
    }

}
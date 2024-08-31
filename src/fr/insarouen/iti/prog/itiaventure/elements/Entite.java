package fr.insarouen.iti.prog.itiaventure.elements;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.EntiteDejaDansUnAutreMondeException;
import java.io.Serializable;

/**
 * Classe Entité, dont hérite objet, structure et vivant. Les Entités sont inclus dans des Mondes.
 * @param nom   Un nom unique désignant l'entité.
 * @param monde Le monde auquel l'entité appartient.
 */
public abstract class Entite implements Serializable{
    private final String nom;
    private Monde monde;

    public Entite(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        this.nom = nom;
        this.monde=monde;
        try {
            monde.ajouter(this);
        }
        catch (EntiteDejaDansUnAutreMondeException e){
            new Error ("Ne doit pas arriver");
        }
    }

    /**
     * Méthode obtenant le nom d'une entité.
     * @return  Le nom de l'entité.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Méthode permettant d'obtenir le monde auquel appartient une entité.
     * @return  Le monde auquel appartient l'entité.
     */
    public Monde getMonde(){
        return this.monde;
    }

    /**
     * Méthode permettant d'obtenir l'entite en cdc.
     * @return  une cdc contenant le nom de l'entite et le monde auquel elle appartient .
     */
    public String toString(){
        return String.format("%s (%s)", this.getNom(),this.getMonde().getNom());
    }

    /**
     * Méthode qui teste l'egalite de deux objets entites.
     * @param objet l'objet qu'on va cast en Entite pour tester l'egalite
     * @return  vrai si ils ont la meme valeur.
     */

    public boolean equals(Object o){
        return (o != null) && (o.getClass() == this.getClass()) && (this.getNom().equals(((Entite)o).getNom())) && (this.getMonde().equals(((Entite)o).getMonde()));
    }

    /**
     * Methode qui retourne le hashCode d'une entite.
     * @return  le hashcode de l'entite.
     */

    public int hashCode(){
        return 11*this.getNom().hashCode() + 13*this.getMonde().hashCode();
    }
}


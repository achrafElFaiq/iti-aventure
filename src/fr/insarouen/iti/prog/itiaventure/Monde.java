package fr.insarouen.iti.prog.itiaventure;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import fr.insarouen.iti.prog.itiaventure.elements.Executable;

    /**
     * Classe définissant un monde du jeu itiaventure. 
     * @param nom       Le nom du monde, les noms doivent être uniques.
     * @param entites   Un tableau comprenant toutes les entités du monde.
     * @see             Entite 
     */
public class Monde implements Serializable{
    private String nom;
    private Map<String, Entite> entites = new HashMap<>();

    public Monde (String nom){
        this.nom=nom;
    }

    /**
     * Méthode permettant d'obtenir le nom d'un monde.
     * @param   nom 
     * @return  Le nom du monde.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Méthode permettant d'obtenir les entités d'un monde.
     * @param nom   Nom de l'entité que l'on veut obtenir, elle doit être dans le monde.
     * @return      L'entité.
     */
    public Entite getEntite(String nom){
        return this.entites.get(nom);
    }

    public Map<String, Executable> getExecutables(){
        Map<String, Executable> executables = new HashMap<>();
        for (Entite entite : this.entites.values()){
            if (entite instanceof Executable){
                executables.put(entite.getNom(), (Executable)entite);
            }
        }
        return Collections.unmodifiableMap(executables);
    }
    /**
     * Méthode ajoutant une entité à un monde.
     * @param ent   L'entité à ajouter.
     */
    public void ajouter(Entite ent) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException{
         if (!ent.getMonde().equals(this))
            throw new EntiteDejaDansUnAutreMondeException("Pas dans le bon monde");
        if(this.getEntite(ent.getNom()) != null) 
            throw new NomDEntiteDejaUtiliseDansLeMondeException("Nom déjà utilisé :" + ent.getNom());
       
        this.entites.put(ent.getNom(), ent);
    }

    /**
     * Méthode toString de monde, donnant le nom du monde et des entités qu'il contient.
     */
    public String toString(){
        StringBuilder s = new StringBuilder(String.format("Le monde %s contient les entités : \n", this.getNom()));
        for (String str: this.entites.keySet()){
            s.append(str);
            s.append(" ; ");
        }

        return new String(s);
    }
    
}
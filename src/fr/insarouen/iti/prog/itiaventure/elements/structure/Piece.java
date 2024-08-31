package fr.insarouen.iti.prog.itiaventure.elements.structure;
import fr.insarouen.iti.prog.itiaventure.elements.objets.*;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
/**
 * Classe Piece, herite de ElementStructurel.
 * @param objets  la liste des objets dans la piece.
 * @param vivants la liste des vivants dans la piece.
 */
public class Piece extends ElementStructurel{
    private Map<String, Objet> objets = new HashMap<>();
    private Map<String, Vivant> vivants = new HashMap<>();
    private Map<String, Porte> portes = new HashMap<>();


    public Piece(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }

    /**
     * Methode qui determine si la piece contient un objet 
     * @param nomObjet le nom de l'objet qu'on cherche dans la piece
     * @return vrai si la piece contient l'objet
     */
    public boolean contientObjet(String nomObjet){
        return this.objets.containsKey(nomObjet);
    }


    /**
     * Methode qui determine si la piece contient un objet 
     * @param objet l'objet qu'on cherche dans la piece
     * @return vrai si la piece contient l'objet
     */
    public boolean contientObjet(Objet objet){
        return this.contientObjet(objet.getNom());
    }

    /**
     * Methode qui retourne les objets contenus dans la piece 
     * @return la liste des objets
     */
    public Map<String, Objet> getObjets(){
        return Collections.unmodifiableMap(this.objets);
    }

    /**
     * Methode qui retourne les vivants contenus dans la piece 
     * @return la liste des vivants
     */
    public Map<String, Vivant> getVivants(){
        return Collections.unmodifiableMap(this.vivants);
    }
    
    /**
     * Methode qui retourne les portes contenuzs dans la piece 
     * @return la liste des portes
     */
    public Map<String, Porte> getPortes(){
        return Collections.unmodifiableMap(this.portes);
    }
  
    /**
     * Methode qui depose un objet dans la piece
     * @param objet l'objet a deposer
     */
    public void deposer(Objet objet){
        this.objets.put(objet.getNom(),objet);
    }

    /**
     * Methode pour retirer un objet de la piece 
     * @param objet l'objet a retirer
     * @return l'objet a retirer
     */
    public Objet retirer(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        
        if(!this.contientObjet(objet)) {
            throw new ObjetAbsentDeLaPieceException(String.format("L'objet %s est absent de la pièce.", objet.toString()));
        }
        if (!objet.estDeplacable()) {
            throw new ObjetNonDeplacableException(String.format("L'objet %s n'est pas déplacable.", objet.toString()));
        }
        return this.objets.remove(objet.getNom());
        
    }

    /**
     * Methode pour retirer un objet de la piece 
     * @param nomObjet le nom de l'objet a retirer
     * @return l'objet a retirer
     */
    public Objet retirer(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        return this.retirer((Objet)this.getMonde().getEntite(nomObjet));
    }


    /**
     * Méthode permettant d'obtenir la piece en cdc.
     * @return  une cdc contenant le nom de la piece et la liste des objets et des vivants .
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("PIECE\n");
        sb.append("OBJETS= ");
        for (String str: this.objets.keySet()){
            sb.append(str);
        }
        sb.append("\nVIVANTS = ");
        for (String str: this.vivants.keySet() ){
            sb.append(str);
        }
        sb.append("\n");
        return sb.toString();
    }


    /**
     * Methode qui determine si la piece contient un vivant 
     * @param nomVivant le nom du vivant qu'on cherche dans la piece
     * @return vrai si la piece contient le vivant
     */
    public boolean contientVivant(String nomVivant){
        return this.vivants.containsKey(nomVivant);
    }

    /**
     * Methode qui determine si la piece contient un vivant 
     * @param nomVivant le nom du vivant qu'on cherche dans la piece
     * @return vrai si la piece contient le vivant
     */

    public boolean contientVivant(Vivant vivant){
        return this.contientVivant(vivant.getNom());
    }


    /**
     * Methode qui permet a un vivant d'entrer dans une piece
     * @param vivant le vivant qui rentre dans la piece
     */

    public void entrer(Vivant vivant){
        this.vivants.put(vivant.getNom(),vivant);
    }

    /**
     * Methode qui permet a un vivant de sortir  d'une piece
     * @param vivant le vivant qui sort de la piece
     */
    public Vivant sortir (Vivant vivant) throws VivantAbsentDeLaPieceException{
        if(this.vivants.remove(vivant.getNom()) == null){            
            throw new VivantAbsentDeLaPieceException("Le vivant " + vivant.getNom() + " n'est pas dans la pièce.");
        }
        return vivant;
    }


    /**
     * Methode qui permet a un vivant de sortir  d'une piece
     * @param vivant le vivant qui sort de la piece
     */
    public Vivant sortir(String nomVivant) throws VivantAbsentDeLaPieceException{
        return this.sortir((Vivant)this.getMonde().getEntite(nomVivant));
    }

    /**
     * Methode qui ajoute une porte a la piece
     * @param porte la porte a ajouter
     */
    protected void addPorte(Porte porte){
        if(!aLaPorte(porte)){
            this.portes.put(porte.getNom(),porte);
        }
    }

    /**
     * Methode qui permet de savoir si la piece a une porte p
     * @param p la porte a tester 
     */
    public boolean aLaPorte(Porte p){
        return this.portes.containsValue(p);
    }

    /**
     *Methode qui permet de savoir si la piece a une porte 
     * @param nomPorte le nom de la porte a tester
     */
    public boolean aLaPorte(String nomPorte){
        return this.portes.containsKey(nomPorte);
    }

    /**
     * Methode qui permet de recuperer l'objet porte correspondant a un nom de porte
     * @param nomPorte nom de la porte
     */
    public Porte getPorte(String nomPorte){
        return this.portes.get(nomPorte);  
    }


        
}

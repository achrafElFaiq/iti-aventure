package fr.insarouen.iti.prog.itiaventure.elements.vivants;
import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.objets.*;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

/**
 * Classe vivant, héritant d'Entité. 
 * @param pointVie  Le nombre de pv de l'entité vivante.
 * @param pointForce   
 * @param piece     La piece dans laquelle l'entité se trouve actuellement.
 * @param objets    tableau des objets possédés par le vivant.
 */
public abstract class Vivant extends Entite{
    int pointVie;
    int pointForce;
    Piece piece;
    Map<String, Objet> objets = new HashMap<>();

    public Vivant(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
        this.pointVie = pointVie;
        this.pointForce = pointForce;
        this.piece = piece;
        piece.entrer(this);
        for (int i=0; i<objets.length ; i++){
            this.objets.put(objets[i].getNom(), objets[i]);
        }
    }

    /**
     * Méthode permettant de savoir où se trouve un vivant.
     * @return La pièce où se trouve le vivant.
     */
    public Piece getPiece(){
        return this.piece;
    }

    /** Méthode permettant d'obtenir les objets du vivant
     * @return Un map des objets du vivants (non modifiable)
     */
    public Map<String, Objet> getObjets(){
        return Collections.unmodifiableMap(this.objets);
    }

    /** 
     * Méthode permettant de connaître la santé d'un vivant.
     * @return  Le nombre de PV du vivant.
     */
    public int getpointVie(){
        return this.pointVie;
    }

    /** 
     * Méthode permettant de modifier la santé d'un vivant.
     */
    public void setpointVie(int pv){
        this.pointVie = pv;
    }

     public int getpointForce(){
        return this.pointForce;
    }

    public boolean estmort(){
        return pointVie == 0;
    }


    /**
     * Methode qui permet a un vivant de prendre un objet elle peut lancer deux exceptions 
     * @param obj l'objet que le vivant prend
     */
    public void prendre(Objet obj) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        this.piece.retirer(obj); // Gestion des erreurs dans retirer
        this.objets.put(obj.getNom(), obj);
    }

     /**
     * Methode qui permet a un vivant de prendre un objet elle peut lancer deux exceptions 
     * @param nomObj le nom de l'objet que le vivant prend
     */
    public void prendre(String nomObj)throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        this.prendre((Objet)this.getMonde().getEntite(nomObj));
    }


     /**
     * Méthode permettant d'obtenir les informations du vivants en cdc.
     * @return  une cdc contenant les informations du vivant.
     */

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("VIVANT = ");
        sb.append(this.getNom() + " ");
        sb.append(this.getMonde().getNom() + " ");
        sb.append(this.pointVie + " ");
        sb.append(this.pointForce + " ");
        for (String str : this.objets.keySet()){
            sb.append(str);
        }
        sb.append("\n");
        return(new String(sb));
    }



    /**
     * Methode qui determine si un vivant contient un objet
     * @return vrai si le vivant possede l'objet
     */
    public boolean possede(Objet obj){
        return this.objets.containsKey(obj.getNom());
    }

    /**
     * Methode qui permet au vivant de deposer un objet dans une piece
     * @param obj l'objet a deposer dans la piece
     */

    public void deposer (Objet obj) throws ObjetNonPossedeParLeVivantException{
        if(!this.possede(obj)){
            throw new ObjetNonPossedeParLeVivantException(this.getNom() + " ne possède pas " + obj.getNom());
        }

        this.piece.deposer(obj);
        this.objets.remove(obj.getNom());
    }    

    
    /**
     * Methode qui permet au vivant de deposer un objet dans une piece
     * @param obj l'objet a deposer dans la piece
     */
    public void deposer(String nomObj) throws ObjetNonPossedeParLeVivantException{
        this.deposer((Objet)this.getMonde().getEntite(nomObj));
    } 

    /**
     * Methode qui permet au vivant de franchir une porte pour passer d'une pièce à une autre.
     * @param porte La porte par laquelle passe le vivant
     */
    public void franchir(Porte porte) throws VivantAbsentDeLaPieceException, PorteFermeeException, PorteInexistanteDansLaPieceException{
        if(!this.piece.aLaPorte(porte)){
            throw new PorteInexistanteDansLaPieceException(String.format("La porte %s n'existe pas dans cette pièce.", porte.getNom()));
        }
        if(porte.getEtat() == Etat.FERME){
            throw new PorteFermeeException(String.format("La porte %s est fermée.", porte.getNom()));
        }
        porte.getPieceAutreCote(this.piece).entrer(this);
        this.piece.sortir(this);
        this.piece = porte.getPieceAutreCote(this.piece);
    }

    /**
     * Methode qui permet au vivant de franchir une porte pour passer d'une pièce à une autre.
     * @param nomPorte Le nom de la porte par laquelle passe le vivant
     */
    public void franchir(String nomPorte) throws VivantAbsentDeLaPieceException, PorteFermeeException, PorteInexistanteDansLaPieceException{
        this.franchir(this.piece.getPorte(nomPorte));
    }
    /**
     * Methode qui pemet d'activer un activable
     * @param activable
     * @throws ActivationException
     */
    public void activerActivable(Activable activable) throws ActivationException{
        activable.activer();
    }
    /**
     * Methode qui permet au vivant d'activer un activable avec un objet
     * @param activable
     * @param obj
     * @throws ActivationException
     */
    public void activerActivableAvecObjet(Activable activable, Objet obj) throws ActivationException{
        activable.activerAvec(obj);
    }
}
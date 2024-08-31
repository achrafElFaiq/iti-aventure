package fr.insarouen.iti.prog.itiaventure.elements.structure;

import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.ITIAventureException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;


public class Porte extends ElementStructurel implements Activable{

    private Piece pieceA;
    private Piece pieceB;
    private Etat etat;
    private Serrure serrure;

    public Porte(String nom,Monde monde,Piece pieceA,Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
        System.out.println(pieceA + " " + pieceB);
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.pieceA.addPorte(this);
        this.pieceB.addPorte(this);
        this.etat = Etat.FERME;
    }
    public Porte(String nom,Monde monde,Serrure serrure,Piece pieceA,Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.pieceA.addPorte(this);
        this.pieceB.addPorte(this);
        this.serrure = serrure;
        if (this.serrure.getEtat().equals(Etat.VERROUILLE)){
            this.etat = Etat.VERROUILLE;
        }
        else {
            this.etat = Etat.FERME;
        }
        
    }

    public void activer() throws ActivationImpossibleException{
        if(this.etat == Etat.VERROUILLE){
            throw new ActivationImpossibleException(String.format("La porte %s est verrouilée",this.getNom()));
        }
        if(this.etat == Etat.FERME){
            this.etat = Etat.OUVERT;
        }
        else{
            this.etat = Etat.FERME;
        }
    }

    public boolean activableAvec(Objet obj){
        return ((this.serrure!=null && this.serrure.activableAvec(obj)) || obj instanceof PiedDeBiche);
    }

    public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException{
        if (this.activableAvec(obj)){
            if (this.serrure.activableAvec(obj)){
                this.serrure.activerAvec(obj);
                if (this.serrure.getEtat().equals(Etat.DEVEROUILLE)){
                    this.etat = Etat.OUVERT;
                }
                else{
                    this.etat = Etat.VERROUILLE;
                }
            }
            else {
                this.etat = Etat.CASSE;
            }
        }
        else{
            throw new ActivationImpossibleAvecObjetException(String.format("L'objet %s n'est pas la clef de la serrure ou un pied de biche.", obj.getNom()));
        }
    }

    public Etat getEtat(){
        return this.etat;
    }

    public Serrure getSerrure(){
        return this.serrure;
    }


    public Piece getPieceAutreCote(Piece piece){
        if(piece.getNom().equals(this.pieceA.getNom())){
            return pieceB;
        }
        return pieceA;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom());
        sb.append(this.getMonde());
        sb.append(this.etat);
        sb.append(this.pieceA);
        sb.append(this.pieceB);
        return new String(sb); 
    }









}
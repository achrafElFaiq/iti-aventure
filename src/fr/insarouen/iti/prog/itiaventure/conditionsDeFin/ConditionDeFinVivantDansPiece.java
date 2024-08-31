package fr.insarouen.iti.prog.itiaventure.conditionsDeFin;

import fr.insarouen.iti.prog.itiaventure.EtatDuJeu;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantDansPiece extends ConditionDeFin{
    
    Vivant vivant;
    Piece piece;

    public ConditionDeFinVivantDansPiece(EtatDuJeu etatDuJeu,Vivant vivant,Piece piece){
        super(etatDuJeu);
        this.vivant = vivant;
        this.piece = piece;
    }

    public EtatDuJeu verifierCondition (){
        if(this.vivant.getPiece().equals(this.piece)) return this.etatDuJeu;
        return EtatDuJeu.ENCOURS;
    }
}
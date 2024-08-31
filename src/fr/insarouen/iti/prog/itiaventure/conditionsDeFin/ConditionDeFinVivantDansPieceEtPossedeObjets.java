package fr.insarouen.iti.prog.itiaventure.conditionsDeFin;

import fr.insarouen.iti.prog.itiaventure.EtatDuJeu;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.itiaventure.conditionsDeFin.DifferentsEtatsDuJeuException;

public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFin{
    ConditionDeFinConjonctionDeConditionDeFin conditionDeFinConj;


    public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etatDuJeu,Vivant vivant,Piece piece,Objet[] objets) throws DifferentsEtatsDuJeuException{
        super(etatDuJeu);
        ConditionDeFinVivantDansPiece conditionDeFinVDP = new ConditionDeFinVivantDansPiece(etatDuJeu,vivant,piece);
        ConditionDeFinVivantPossedeObjets conditionDeFinPO = new ConditionDeFinVivantPossedeObjets(etatDuJeu,vivant,objets);
        this.conditionDeFinConj = new ConditionDeFinConjonctionDeConditionDeFin(etatDuJeu, conditionDeFinPO, conditionDeFinVDP);
    }

    public EtatDuJeu verifierCondition (){
        return this.conditionDeFinConj.verifierCondition();
    }
}
package fr.insarouen.iti.prog.itiaventure.conditionsDeFin;

import fr.insarouen.iti.prog.itiaventure.EtatDuJeu;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;


public class ConditionDeFinVivantMort extends ConditionDeFin{
    private Vivant vivant;

    public ConditionDeFinVivantMort(EtatDuJeu etatDuJeu, Vivant vivant){
        super(etatDuJeu);
        this.vivant = vivant;
    }

    public EtatDuJeu verifierCondition (){
        if (this.vivant.estmort()){
            return this.etatDuJeu;
        }
        return EtatDuJeu.ENCOURS;
    }
}
package fr.insarouen.iti.prog.itiaventure.conditionsDeFin;

import fr.insarouen.iti.prog.itiaventure.EtatDuJeu;

public abstract class ConditionDeFin{
    EtatDuJeu etatDuJeu; 

    public ConditionDeFin(EtatDuJeu etatDuJeu){
        this.etatDuJeu = etatDuJeu;
    }

    public abstract EtatDuJeu verifierCondition ();
}
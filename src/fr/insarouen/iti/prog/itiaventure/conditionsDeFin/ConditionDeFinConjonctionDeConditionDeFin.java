package fr.insarouen.iti.prog.itiaventure.conditionsDeFin;

import fr.insarouen.iti.prog.itiaventure.EtatDuJeu;
import fr.insarouen.iti.prog.itiaventure.conditionsDeFin.DifferentsEtatsDuJeuException;

public class ConditionDeFinConjonctionDeConditionDeFin extends ConditionDeFin{
    
    ConditionDeFin[] conditions;

    public ConditionDeFinConjonctionDeConditionDeFin(EtatDuJeu etatDuJeu,ConditionDeFin... conditions) throws DifferentsEtatsDuJeuException{
        super(etatDuJeu);
        this.conditions = new ConditionDeFin[conditions.length];
        for (int i=0;i<conditions.length;i++){
            this.conditions[i] = conditions[i];
        }
        for (int i=0;i<this.conditions.length;i++){
            if (conditions[i].etatDuJeu!=this.etatDuJeu) throw new DifferentsEtatsDuJeuException();
        }
    }

    public EtatDuJeu verifierCondition (){
        boolean conditionsEgalesEtatDuJeu = true;
        int i = 0;
        while(conditionsEgalesEtatDuJeu && i<this.conditions.length){
            if (!(this.conditions[i].verifierCondition().equals(this.etatDuJeu))) conditionsEgalesEtatDuJeu = false;
            i++;
        }
        if (conditionsEgalesEtatDuJeu) return this.etatDuJeu;
        return EtatDuJeu.ENCOURS;
    }
}

    
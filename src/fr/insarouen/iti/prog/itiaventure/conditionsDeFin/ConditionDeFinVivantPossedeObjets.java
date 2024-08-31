package fr.insarouen.iti.prog.itiaventure.conditionsDeFin;

import fr.insarouen.iti.prog.itiaventure.EtatDuJeu;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin{
    Vivant vivant;
    Objet[] objets;

    public ConditionDeFinVivantPossedeObjets(EtatDuJeu etatDuJeu,Vivant vivant,Objet[] objets){
        super(etatDuJeu);
        this.vivant = vivant;
        this.objets = new Objet[objets.length];
        for (int i=0;i<objets.length;i++){
            this.objets[i] = objets[i];
        }
    }

    public EtatDuJeu verifierCondition (){
        boolean contientObjets = true;
        int i=0;
        while(i<this.objets.length && contientObjets){
            if(!(this.vivant.possede(this.objets[i]))) contientObjets=false;
            i++;
        }
        if(contientObjets) return this.etatDuJeu;
        return EtatDuJeu.ENCOURS;
    }
}
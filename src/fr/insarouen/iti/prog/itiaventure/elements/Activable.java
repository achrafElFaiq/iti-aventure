package fr.insarouen.iti.prog.itiaventure.elements;

import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;


/**
     *  Interface définissant tout ce qui est activable. 
*/
public interface Activable{
    /**
     * Méthode qui permet de savoir si un objet peut activer un activable.
     * @param   obj 
     * @return  vrai si objet active l'objet activable , faux sinon.
     */
    boolean activableAvec(Objet obj);

    /**
     * Methode qui permet d'activer un activable
     */
    void activer() throws ActivationException;

    /**
     * Methode qui permet d'activer un activable avec un objet
     * @param obj
     */
    void activerAvec(Objet obj) throws ActivationException;
}
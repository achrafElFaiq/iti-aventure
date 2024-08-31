package fr.insarouen.iti.prog.itiaventure.elements;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class EntiteTest extends Entite {

    public EntiteTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde);
    } 

    public static void main(String[] args) throws NomDEntiteDejaUtiliseDansLeMondeException{
        Monde monde = new Monde("monde");
        Entite e = new EntiteTest("nom", monde);
        Entite e2 = new EntiteTest("autre", monde);
        System.out.println(e.toString());
        if (e.equals(e2)){
            System.out.println("Sont égaux.");
        } else {
            System.out.println("Sont différents.");
        }
    }
}
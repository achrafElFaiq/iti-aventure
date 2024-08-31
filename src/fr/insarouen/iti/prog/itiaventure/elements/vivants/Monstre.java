package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import java.util.Random;
import java.util.Map;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;

public class Monstre extends Vivant implements Executable{
    public Monstre(String nom, Monde monde, int pv, int pointForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde, pv, pointForce, piece, objets);
    }

    /**
     * Methode qui execute un tour de vie d'un monstre
     * @throws Throwable
     */
    public void executer() throws Throwable{
        if (!this.estmort()){
                Random generator = new Random();
                Object[] portes = piece.getPortes().values().toArray();
                Porte porte = (Porte)portes[generator.nextInt(portes.length)];
            if (porte.getEtat() != Etat.VERROUILLE){
                if (porte.getEtat() == Etat.FERME){
                    porte.activer();
                }
                this.franchir(porte);
                this.setpointVie(this.getpointVie() - 1);
                Objet[] objetsDansLaPiece = this.piece.getObjets().values().toArray(new Objet[0]);
                Objet[] objetsDuMonstre = this.getObjets().values().toArray(new Objet[0]);
                for (Objet key : objetsDuMonstre){
                    this.deposer(key);
                }
                for (Objet key : objetsDansLaPiece){
                    this.prendre(key);
                }
            }
            
        }
    }
}
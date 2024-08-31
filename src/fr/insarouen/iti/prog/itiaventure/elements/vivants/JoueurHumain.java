package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.Arrays;

import fr.insarouen.iti.prog.itiaventure.elements.Entite;
import fr.insarouen.iti.prog.itiaventure.elements.Activable;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeeException;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.CommandeImpossiblePourLeVivantException;


public class JoueurHumain extends Vivant implements Executable{
    String ordre;



    public JoueurHumain(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde, pointVie, pointForce, piece, objets);
    }
    /**
     * Methode qui permet de donner un ordre au joueur humain
     * @param ordre
     * 
     */
    public void setOrdre(String ordre){
        this.ordre = ordre;
    }


    /**
     * Methode qui permet au joueur de prendre un objet
     * @param nomObjet
     * @throws ObjetAbsentDeLaPieceException
     * @throws ObjetNonDeplacableException
     */
    public void commandePrendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        System.out.println(this.getObjets());
        this.prendre(nomObjet);
        System.out.println(this.getObjets());
    }

    /**
     * Methode qui permet au joueur de poser un objet
     * @param nomObjet
     * @throws ObjetNonPossedeParLeVivantException
     */
    public void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException {
        this.deposer(nomObjet);
    }
    /**
     * Methode qui permet au joueur de franchir une 
     * @param nomPorte
     * @throws VivantAbsentDeLaPieceException
     * @throws PorteFermeeException
     * @throws PorteInexistanteDansLaPieceException
     */
    public void commandeFranchir(String nomPorte) throws VivantAbsentDeLaPieceException, PorteFermeeException, PorteInexistanteDansLaPieceException {
        this.franchir(nomPorte);
    }

    /**
     * Methode qui permet au joueur d'ouvrir une porte sans Objet
     * @param nomPorte
     * @throws ActivationException
     * @throws PorteInexistanteDansLaPieceException
     */
    public void commandeOuvrirPorte(String nomPorte) throws ActivationException, PorteInexistanteDansLaPieceException {
        this.activerActivable((Porte)this.getMonde().getEntite(nomPorte));
    }


    /**
     * Methode qui permet au joueur d'ouvrir une porte avec un objet (cle ou pied de biche)
     * @param nomPorte
     * @param nomObjet
     * @throws ActivationException
     * @throws PorteInexistanteDansLaPieceException
     * @throws ObjetNonPossedeParLeVivantException
     */
    public void commandeOuvrirPorte( String nomObjet, String nomPorte) throws ActivationException, PorteInexistanteDansLaPieceException,ObjetNonPossedeParLeVivantException {
        this.activerActivableAvecObjet((Porte)this.getMonde().getEntite(nomPorte), (Objet)this.getMonde().getEntite(nomObjet));
    }

    public void executer() throws CommandeImpossiblePourLeVivantException, Throwable {
        Scanner sc = new Scanner(ordre);
        String commande = sc.next();
        Map<String, Class> params = new HashMap<>();
        Class<?> JH = Class.forName("fr.insarouen.iti.prog.itiaventure.elements.vivants.JoueurHumain");
        while (sc.hasNext()){
            params.put(sc.next(), String.class);
        }
        Class[] params_tab = new Class[params.values().toArray().length];
       
        params_tab=params.values().toArray(params_tab);
        Method method = JH.getMethod("commande"+commande, params_tab);
        String[] stringObjs = new String[params.keySet().toArray().length];
        stringObjs = params.keySet().toArray(stringObjs);
        method.invoke(this,(Object[])stringObjs);
    }
}
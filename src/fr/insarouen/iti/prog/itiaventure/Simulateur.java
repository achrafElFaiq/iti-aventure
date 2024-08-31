package fr.insarouen.iti.prog.itiaventure;
import java.io.Reader;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Monstre;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Coffre;
import fr.insarouen.iti.prog.itiaventure.conditionsDeFin.ConditionDeFin;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.JoueurHumain;
import fr.insarouen.iti.prog.itiaventure.EtatDuJeu;
import fr.insarouen.iti.prog.itiaventure.elements.Executable;
import fr.insarouen.iti.prog.itiaventure.conditionsDeFin.*;
/**
 * La classe Simulateur permet de créer un monde avec ses conditions de victoire (ou de défaite).
 * Elle permet de le créer de trois façon : 
 * 1) En lui donnant directement le monde à créer et ses conditions.
 * 2) En lui donnant un ObjectInputStream correspondant à une sauvegarde du monde. Celui-ci est désérialiser pour recharger le monde.
 * 3) En lui donnant un fichier texte avec les caractéristiques du monde. À noter que ce fichier texte est supposer sans erreur,
 *    donner les éléments en commençant par le haut de l'arbre de création (ie le monde avec son nom), donner les attributs de chaque éléments à créer dans l'ordre de l'initialiseur.
 */
public class Simulateur{

    public Monde monde;
    List<ConditionDeFin> conditionsDeFin = new ArrayList<>();
    EtatDuJeu etatDuJeu;
    Scanner chat = new Scanner(System.in);


    /**
     * 1) Création via un monde et ses conditions de fin
     * @param monde
     * @param conditionsDeFin
     */
    public Simulateur (Monde monde, ConditionDeFin... conditionsDeFin){
        this.monde = monde;
        this.conditionsDeFin = Arrays.asList(conditionsDeFin);
    }

    /** 
     * 2) Création via une sauvegarde.
     * @param ois   L'ObjectInputStream, le flux sérialisé d'objets correspondant à la sauvegarde.
     */
    public Simulateur(java.io.ObjectInputStream ois) throws IOException, ClassNotFoundException{
        this.monde = (Monde)ois.readObject();
        this.conditionsDeFin = (List<ConditionDeFin>)ois.readObject();
    }

    /**
     * 3) Création via un fichier texte.
     * @param reader    Le fichier ouvert en lecture.
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    public Simulateur (Reader reader) throws NomDEntiteDejaUtiliseDansLeMondeException, DifferentsEtatsDuJeuException{
        Scanner sc = new Scanner(reader);
        
        while(sc.hasNext()){
            String mot = sc.next();

            switch(mot){
                case "Monde":
                    this.monde = createMonde(sc);
                    System.out.println("Monde créé " + this.monde.getNom());
                    break;
                    

                case "Piece":
                    Piece piece = createPiece(sc,this.monde);
                    System.out.println("Pièce créée " + piece.getNom());
                    break;

                case "PorteSerrure":
                    
                    Porte porteS = createPorteSerrure(sc,this.monde);
                    System.out.println("Porte avec serrure créée "+ porteS.getNom());
                    break;

                case "Porte":
                    
                    Porte porte = createPorte(sc,this.monde);
                    System.out.println("Porte créée "+ porte.getNom());
                    break;

                case "Clef":
                    Clef clef = createClef(sc,this.monde);
                    Piece _piece = (Piece) this.monde.getEntite(sc.next().replaceAll("\"", ""));
                    _piece.deposer(clef);
                    System.out.println("Clef créée "+ clef.getNom());
                    break;

                case "Coffre":
                    Coffre coffre = createCoffre(sc,this.monde);
                    System.out.println("Coffre créé "+ coffre.getNom());
                    break;

                case "PiedDeBiche":
                    PiedDeBiche pdb = createPdb(sc,this.monde);
                    System.out.println("Pied de biche créé "+ pdb.getNom());
                    break;

                case "JoueurHumain":
                    JoueurHumain jh = createJoueurHumain(sc, this.monde);
                    System.out.println("Joueur créé "+ jh.getNom());
                    break;

                case "Monstre":
                    Monstre monstre = createMonstre(sc, this.monde);
                    System.out.println("Monstre créé "+ monstre.getNom());
                    break;

                case "ConditionDeFinVivantDansPiece":
                    ConditionDeFinVivantDansPiece cdfVDP = createCDFVivantDansPiece(sc, this.monde);
                    conditionsDeFin.add(cdfVDP);
                    System.out.println("CDF créée");
                    break;

                case "ConditionDeFinVivantDansPieceEtPossedeobjets":
                    ConditionDeFinVivantDansPieceEtPossedeObjets cdfVDPEPO = createCDFVivantDansPieceEtPossedeObjets(sc, this.monde);
                    conditionsDeFin.add(cdfVDPEPO);
                    System.out.println("CDF créée");
                    break;

                case "ConditionDeFinVivantMort":
                    ConditionDeFinVivantMort cdfVM = createCDFVivantMort(sc, this.monde);
                    conditionsDeFin.add(cdfVM);
                    System.out.println("CDF créée");
                    break;
                
                case "ConditionDeFinVivantPossedeObjets":
                    ConditionDeFinVivantPossedeObjets cdfVPO = createCDFVivantPossedeObjets(sc, this.monde);
                    conditionsDeFin.add(cdfVPO);
                    System.out.println("CDF créée");
                    break;
            } 
        }
    }

    /**
     * Méthode décrivant au joueur la pièce dans laquelle il se trouve et ce qu'elle contient.
     * @param jh Le joueur
     */
    public void decrireEtat(JoueurHumain jh){
        Piece piece = jh.getPiece();

        // Nom pièce 
        System.out.println(String.format("Vous êtes dans la pièce %s", piece.getNom()));
       
       // Nom(s) vivant(s)
        Map<String, Vivant> vivants = piece.getVivants();
        if (vivants.size() == 0){
            System.out.println("Vous êtes seul");
        }
        else {
            System.out.println(String.format("Il y a avec vous %s", vivants.keySet()));
        }

        // Nom(s) objet(s) dans pièce
        Map<String, Objet> objets = piece.getObjets();
        if (objets.size() == 0){
            System.out.println("Il n'y a rien à récupérer ici...");
        }
        else {
            System.out.println(String.format("Il y a devant vous %s", objets.keySet()));
        }

        // Nom(s) porte(s)
        System.out.println(String.format("Vous voyez également %d porte(s) : %s", piece.getPortes().size(), piece.getPortes().keySet()));

        // Nom(s) objet(s) possédé(s)
        Map<String, Objet> objetsP = jh.getObjets();
        if (objetsP.size() == 0){
            System.out.println("Vous n'avez pas encore d'objets.");
        }
        else {
            System.out.println(String.format("Vous avez : %s", objetsP.keySet()));
        }

        System.out.println("Veillez choisir une action parmis : \n");
        System.out.println("Prendre (nom de l'objet) \n");
        System.out.println("Poser (nom de l'objet) \n");
        System.out.println("Franchir (nom de la porte) \n");
        System.out.println("OuvrirPorte (nom de la porte) \n");
        System.out.println("OuvrirPorte (nom de la porte) (nom de la clé) \n");
    }

    /**
     * Méthode exécutant un tour du jeu, en faisant agir toutes les entités le pouvant (ie les executables),
     * puis en vérifiant l'état du jeu à la fin du tour.
     * @throws Throwable
     */
    public EtatDuJeu executerUnTour() throws Throwable{

        // Action des executables
        for (Executable executable:this.monde.getExecutables().values()){
            if (executable instanceof JoueurHumain){
                decrireEtat((JoueurHumain)executable);
                String ordre = chat.nextLine();
                //System.out.println("Ordre "+ordre);
                JoueurHumain jh = (JoueurHumain)executable;
                jh.setOrdre(ordre);
            }
            executable.executer();
        }

        // Vérification des conditions de fin
        EtatDuJeu etat = EtatDuJeu.ENCOURS;
        for (ConditionDeFin condition:conditionsDeFin){
            etat = condition.verifierCondition();
            if (etat != EtatDuJeu.ENCOURS){
                break;
            }
        }
        return etat;
    }

    /**
     * Methode qui permet de créer un monde
     * @param sc
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private Monde createMonde(Scanner sc) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Monde(sc.next().replaceAll("\"", ""));
    }
    /**
     * Methode qui permet de creer une piece
     * @param sc
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private Piece createPiece(Scanner sc,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        String nomPiece = sc.next();
        return new Piece(nomPiece.replaceAll("\"", ""),monde);
    }

    /**
     * methode qui permet de creer une porte sans serrure
     * @param sc
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private Porte createPorte(Scanner sc,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Porte(sc.next().replaceAll("\"", ""),monde,(Piece)monde.getEntite(sc.next().replaceAll("\"", "")),(Piece)monde.getEntite(sc.next().replaceAll("\"", "")));
    }

    /**
     * Methode qui permet de creer une serrure 
     * @param nom
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private Serrure createSerrure(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Serrure(nom.replaceAll("\"", ""),monde);
    }

    /**
     * Methode qui permet de creer une Porte avec serrure
     * @param sc
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private Porte createPorteSerrure(Scanner sc,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        String nomPorte = sc.next();
        Serrure serrure = createSerrure(String.format("SerrureDe%s",nomPorte),monde);
        return new Porte(nomPorte.replaceAll("\"", ""),monde,serrure,(Piece)monde.getEntite(sc.next().replaceAll("\"", "")),(Piece)monde.getEntite(sc.next().replaceAll("\"", "")));
    }

    /**
     * methode qui permet de creer une clef
     * @param sc
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private Clef createClef(Scanner sc,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        Porte _porte = (Porte)monde.getEntite(sc.next().replaceAll("\"", ""));
        Serrure serrure = _porte.getSerrure();
        return serrure.creerClef();
    }

    /**
     * methode qui ermet la creation d'un joueur humain
     * @param sc
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private JoueurHumain createJoueurHumain(Scanner sc,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        String nom = sc.next().replaceAll("\"", "");
        Integer pv = Integer.valueOf(sc.next());
        Integer pf = Integer.valueOf(sc.next());
        Piece piece = (Piece)monde.getEntite(sc.next().replaceAll("\"", ""));
        Integer nb_obj = Integer.valueOf(sc.next());
        Objet[] objets = new Objet[nb_obj];
        for (int nb=0 ; nb<nb_obj;++nb){
            objets[nb] = (Objet)monde.getEntite(sc.next().replaceAll("\"", ""));
        }
        return new JoueurHumain(nom,monde,pv,pf, piece, objets);
    }
    /**
     * Methode qui permet la creation d'un monstre
     * @param sc
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private Monstre createMonstre(Scanner sc,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        String nom = sc.next().replaceAll("\"", "");
        Integer pv = Integer.valueOf(sc.next());
        Integer pf = Integer.valueOf(sc.next());
        Piece piece = (Piece)monde.getEntite(sc.next());
        Integer nb_obj = Integer.valueOf(sc.next());
        Objet[] objets = new Objet[nb_obj];
        for (int nb=0 ; nb<nb_obj;++nb){
            objets[nb] = (Objet)monde.getEntite(sc.next().replaceAll("\"", ""));
        }
        return new Monstre(nom,monde,pv,pf, piece, objets);
    }

    /**
     * Methode qui permet de creer un coffre
     * @param sc
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private Coffre createCoffre(Scanner sc,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new Coffre(sc.next().replaceAll("\"", ""), monde);
    }

    /**
     * Methode qui permet de creer un pied de biche
     * @param sc
     * @param monde
     * @return
     * @throws NomDEntiteDejaUtiliseDansLeMondeException
     */
    private PiedDeBiche createPdb(Scanner sc,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        return new PiedDeBiche(sc.next().replaceAll("\"", ""), monde);
    }

    private ConditionDeFinVivantDansPiece createCDFVivantDansPiece(Scanner sc, Monde monde){
        String edj = sc.next();
        String vivant = sc.next().replaceAll("\"", "");
        String piece = sc.next().replaceAll("\"", "");
        return new ConditionDeFinVivantDansPiece(EtatDuJeu.valueOf(edj),(Vivant) monde.getEntite(vivant),(Piece)monde.getEntite(piece));

    }

    private ConditionDeFinVivantMort createCDFVivantMort(Scanner sc,Monde monde){
        String edj = sc.next();
        String vivant = sc.next();

        return new ConditionDeFinVivantMort(EtatDuJeu.valueOf(edj),(Vivant) monde.getEntite(vivant));

    }

    private ConditionDeFinVivantPossedeObjets createCDFVivantPossedeObjets(Scanner sc, Monde monde){
        String edj = sc.next().replaceAll("\"", "");
        String vivant = sc.next().replaceAll("\"", "");
        int nbObjets = Integer.valueOf(sc.next());
        Objet[] objets = new Objet[nbObjets];
        for (int i=0;i<nbObjets;i++){
            objets[i] = (Objet) monde.getEntite(sc.next().replaceAll("\"", ""));
        }
        return new ConditionDeFinVivantPossedeObjets(EtatDuJeu.valueOf(edj),(Vivant)monde.getEntite(vivant),objets);
    }

    private ConditionDeFinVivantDansPieceEtPossedeObjets createCDFVivantDansPieceEtPossedeObjets(Scanner sc,Monde monde) throws DifferentsEtatsDuJeuException{
        String edj = sc.next();
        String vivant = sc.next().replaceAll("\"", "");
        String piece = sc.next().replaceAll("\"", "");
        int nbObjets = Integer.valueOf(sc.next());
        Objet[] objets = new Objet[nbObjets];
        for (int i=0;i<nbObjets;i++){
            objets[i] = (Objet) monde.getEntite(sc.next().replaceAll("\"", ""));
        }
        return new ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu.valueOf(edj),(Vivant)monde.getEntite(vivant),(Piece)monde.getEntite(piece),objets);
    }
}
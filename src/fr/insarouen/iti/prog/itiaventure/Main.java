package fr.insarouen.iti.prog.itiaventure;

import java.util.Scanner;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.Simulateur;

public class Main{
    public static void main(String[] args) throws java.lang.Throwable{
        boolean quitter = false;
        Simulateur simulateur = null;
        Scanner sc = new Scanner(System.in);

        while (!quitter){
            afficherMenu();
            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    jouer(simulateur, sc);
                    break;
                
                case 2:
                    simulateur = chargerFichier(sc);
                    break;
                
                case 3:
                    if (simulateur != null){
                        sauvegarder(simulateur, sc);
                    }
                    else{
                        System.out.println("Vous n'avez pas de partie en cours.");
                    }
                    break;
                
                case 4:
                    simulateur = chargerSauvegarde(sc);
                    break;
                
                case 5:
                    quitter = true;
                    break;
        }
        
        }
    }
    /**
     * Methode qui propose les differentes actions possible au lancement du jeu
     */
    private static void afficherMenu(){
        System.out.println(" --- Menu --- \n");
        System.out.println("1/ jouer ");
        System.out.println("2/ charger un fichier de description ");
        System.out.println("3/ sauver la partie actuelle ");
        System.out.println("4/ charger une partie ");
        System.out.println("5/ quitter ");
    }

    private static void jouer(Simulateur simulateur, Scanner sc) throws Throwable{
        boolean continuer = true;
        EtatDuJeu edj = EtatDuJeu.ENCOURS;
        while (continuer && (edj == EtatDuJeu.ENCOURS)){
            edj = simulateur.executerUnTour();
            switch (edj){
                case EtatDuJeu.ENCOURS:
                    System.out.println("Souhaitez-vous continuer à jouer ? o/n");
                    String reponse = sc.next();
                    continuer = (reponse.equals("o"));
                    break;
                case EtatDuJeu.SUCCESS:
                    System.out.println("Vous avez gagné !");
                    continuer = false;
                    break;
                case EtatDuJeu.ECHEC:
                    System.out.println("Vous avez perdu...");
                    continuer = false;
                    break;
            }
            
        }
    }
    /**
     * Methode qui permet de charger un monde a partir de fichier txt
     * @param sc
     * @return
     */
    private static Simulateur chargerFichier(Scanner sc) throws Throwable{
        try{
            System.out.println("Quel fichier charger ?");
            String nom_fichier = "exemplesimulation1.txt";//sc.next();
            Reader reader = new BufferedReader(new FileReader(nom_fichier));
            Simulateur simulateur = new Simulateur(reader);
            System.out.println("Le fichier a correctement créé le monde.");
            return simulateur;
        }
        catch (FileNotFoundException exception_fichier){
            System.out.println("Le fichier n'a pas pu être chargé.");
        }
        catch (NomDEntiteDejaUtiliseDansLeMondeException exception_creation){
            System.out.println("Le monde n'a pas pu être correctement créé.");
        }
        /*catch (Throwable exception_inconnue){
            System.out.println("Une erreur inconnue s'est produite.");
        } */
        return null;
    }
    /**
     * Methode qui permet d'enregistrer une partie
     * @param simulateur
     * @param sc
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void sauvegarder(Simulateur simulateur, Scanner sc) throws FileNotFoundException, IOException{
        System.out.println("Nom de la sauvegarde :");
        String nomFichier = sc.next();
        FileOutputStream fos = new FileOutputStream(nomFichier);
        ObjectOutputStream sauvegarde = new ObjectOutputStream(fos);
        sauvegarde.writeObject(simulateur.monde);
        sauvegarde.writeObject(simulateur.conditionsDeFin);
        sauvegarde.close();
    }


    /**
     * Methode qui permet de charger une partie deja sauvegardee
     * @param sc
     * @return
     */
    private static Simulateur chargerSauvegarde(Scanner sc){
        try {
            System.out.println("Quel sauvegarde charger ?");
            String nomSauvegarde = sc.next();
            FileInputStream fis = new FileInputStream(nomSauvegarde);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Simulateur simulateur = new Simulateur(ois);
            return simulateur;
        }
       catch (FileNotFoundException exception_fichier){
            System.out.println("La sauvegarde n'a pas pu être chargée car le fichier n'a pas été trouvé.");
       }
       catch (IOException exception_entree_sortie){
            System.out.println("Erreur d'entrée-sortie.");
       }
       catch (ClassNotFoundException exception_ClassNotFoundException){
            System.out.println("Class not found.");
       }
       return null;
    }
}



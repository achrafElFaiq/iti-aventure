package fr.insarouen.iti.prog.itiaventure.elements.vivants;
import fr.insarouen.iti.prog.itiaventure.elements.objets.*;
import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.VivantAbsentDeLaPieceException;


public class VivantTest extends Vivant{
    public VivantTest(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom, monde, pointVie, pointForce, piece, objets);
    }

 public static void main(String[] args){
    try {
        Monde monde = new Monde("INSA ROUEN");
        Piece piece = new Piece("salle TP", monde);
        PiedDeBiche pdb = new PiedDeBiche("pdb",monde);
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1",monde);
        PiedDeBiche pdb2 = new PiedDeBiche("pdb2",monde);
        Vivant v = new VivantTest("Malandain", monde, 3, 2, piece, pdb, pdb1, pdb2);

        PiedDeBiche pdb3 = new PiedDeBiche("pdb3",monde);
        PiedDeBiche pdb4 = new PiedDeBiche("pdb4",monde);
        PiedDeBiche pdb5 = new PiedDeBiche("pdb5",monde);


        piece.deposer(pdb3);
        piece.deposer(pdb4);
        piece.deposer(pdb5);

        v.prendre(pdb3);
        v.prendre("pdb4");

        System.out.println(piece.toString());
        System.out.println(v.toString());

        v.deposer(pdb4);

        System.out.println(piece.toString());
        System.out.println(v.toString());

        piece.entrer(v);
        System.out.println(piece.toString());


        piece.sortir(v);
        System.out.println("apres sortir "+piece.toString());
        }
        catch (NomDEntiteDejaUtiliseDansLeMondeException e){
        }
        catch (ObjetAbsentDeLaPieceException s){}
        catch (ObjetNonDeplacableException o){}
        catch (VivantAbsentDeLaPieceException v){}
        catch (ObjetNonPossedeParLeVivantException np){}
    }
}
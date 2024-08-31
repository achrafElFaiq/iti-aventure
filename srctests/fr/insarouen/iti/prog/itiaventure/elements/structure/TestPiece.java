package fr.insarouen.iti.prog.itiaventure.elements.structure;
import org.junit.Test;
import org.junit.Before;
import fr.insarouen.iti.prog.itiaventure.elements.objets.Objet;
import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.itiaventure.Monde;
import static org.hamcrest.MatcherAssert.assertThat;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.Is;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.VivantTest;
import java.util.Collection;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import org.hamcrest.core.IsNull;

public class TestPiece{
    Piece piece;
    Piece pieceB;
    Monde insa;
    PiedDeBiche ordi;
    Vivant vivant;
    Porte porte;

    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException{
        insa = new Monde("INSA");
        piece = new Piece("Salle TP", insa);
        pieceB = new Piece("Salle TD", insa);
        ordi = new PiedDeBiche("ordi", insa);
        vivant = new VivantTest("Elouen",insa,10,10,piece);
        porte = new Porte("porte", insa,piece,pieceB);
    }

    @Test 
    public void testDeposer(){
        assertThat(piece.getObjets().containsValue(ordi), Is.is(false));
        piece.deposer(ordi);
        assertThat(piece.contientObjet("ordi"), Is.is(true));
    }

    @Test
    public void testContientObjet(){
        assertThat(piece.contientObjet("ordi"), Is.is(false));
    }

    @Test
    public void testRetirer() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        piece.deposer(ordi);
        piece.retirer(ordi);
        assertThat(piece.getObjets().containsValue(ordi), Is.is(false));
    }


    @Test 
    public void testContientVivant(){
        assertThat(piece.contientVivant("Elouen"), Is.is(true));
        assertThat(piece.contientVivant("ordi"), Is.is(false));
    }

    @Test
    public void testEntrer()throws NomDEntiteDejaUtiliseDansLeMondeException{
        assertThat(piece.contientVivant("Elouan"), Is.is(false));
        vivant = new VivantTest("Elouan",insa,10,10,piece);
        assertThat(piece.contientVivant("Elouan"), Is.is(true));

    } 

    @Test 
    public void testSortir() throws VivantAbsentDeLaPieceException{
        assertThat(piece.contientVivant("Elouen"), Is.is(true));
        piece.sortir(vivant);
        assertThat(piece.contientVivant("Elouen"), Is.is(false));
    }


    @Test (expected = VivantAbsentDeLaPieceException.class)
    public void testVivantAbsentDeLaPieceException() throws NomDEntiteDejaUtiliseDansLeMondeException, VivantAbsentDeLaPieceException{
        Piece piece2 = new Piece("piece", insa);
        piece2.sortir(vivant);
    }

    @Test 
    public void testAddPorte(){
        assertThat(piece.aLaPorte(porte),Is.is(true));
    }

    @Test
    public void testGetPorte(){
        assertThat(piece.getPorte("porte"),Is.is(porte));
        assertThat(piece.getPorte("pote"),IsNull.nullValue());
    }


}
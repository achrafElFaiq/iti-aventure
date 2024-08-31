package fr.insarouen.iti.prog.itiaventure.elements.vivants;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetTest;
import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;


public class TestVivant {
    VivantTest vt;
    Monde monde;
    Monde autre_monde;
    PiedDeBiche pdb;
    PiedDeBiche obj;
    Piece piece;
    Piece autre_piece;
    Porte porte;
    Piece autre_piece2;
    Porte porte2;

    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException{
        monde = new Monde("Monde test");
        autre_monde = new Monde("Autre monde");
        piece = new Piece("Une piece", monde);
        autre_piece = new Piece("Une autre piece", monde);
        porte = new Porte("porte", monde, piece, autre_piece);
        pdb = new PiedDeBiche("pdb", monde);
        vt = new VivantTest("Prototype", monde, 10, 5, piece, pdb);
        obj = new PiedDeBiche("truc", monde);
    }

    @Test
    public void testInitAndGetter(){
        assertThat(vt.getMonde(), Is.is(monde));
        assertThat(vt.getMonde(), IsNot.not(autre_monde));
        assertThat(vt.getNom(), Is.is("Prototype"));
        assertThat(vt.getNom(), IsNot.not("Pas prototype"));
        assertThat(vt.getObjets().containsValue(pdb), Is.is(true));
        assertThat(vt.getObjets().containsValue(obj), Is.is(false));
        assertThat(vt.getpointForce(), Is.is(5));
        assertThat(vt.getpointForce(), IsNot.not(10));
        assertThat(vt.getpointVie(), Is.is(10));
        assertThat(vt.getpointVie(), IsNot.not(5));
        assertThat(vt.getPiece(), Is.is(piece));
        assertThat(vt.getPiece(), IsNot.not(autre_piece));
    }
 
    @Test
    public void testEstMort() throws NomDEntiteDejaUtiliseDansLeMondeException{
        assertThat(vt.estmort(), Is.is(false));
        VivantTest vt2;
        vt2 = new VivantTest("Prototype 2", monde, 0, 5, piece, pdb);
        assertThat(vt2.estmort(), Is.is(true));
    }

    @Test
    public void testPrendreDeposerPossede() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException, ObjetNonPossedeParLeVivantException{
        assertThat(vt.possede(obj), Is.is(false));
        assertThat(vt.piece.contientObjet(obj), Is.is(false));
        vt.piece.deposer(obj);
        assertThat(vt.piece.contientObjet(obj), Is.is(true));
        vt.prendre(obj);
        assertThat(vt.piece.contientObjet(obj), Is.is(false));
        assertThat(vt.possede(obj), Is.is(true));
        vt.deposer(obj);
        assertThat(vt.possede(obj), Is.is(false));
        assertThat(vt.piece.contientObjet(obj), Is.is(true));
    }


    @Test(expected = ObjetAbsentDeLaPieceException.class)
    public void testObjetAbsentDeLaPieceException() throws ObjetNonDeplacableException, ObjetAbsentDeLaPieceException{
        vt.prendre(obj);
    }

    @Test(expected = ObjetNonDeplacableException.class)
    public void testObjetNonDeplacableException() throws ObjetNonDeplacableException, NomDEntiteDejaUtiliseDansLeMondeException, ObjetAbsentDeLaPieceException{
        ObjetTest objet = new ObjetTest("nom", monde);
        piece.deposer(objet);
        vt.prendre(objet);
    }

    @Test(expected = ObjetNonPossedeParLeVivantException.class)
    public void testObjetNonPossedeParLeVivantException() throws ObjetNonPossedeParLeVivantException{
        vt.deposer(pdb);
        vt.deposer(pdb);
    }

    @Test
    public void testFranchirPorte() throws ActivationImpossibleException, PorteInexistanteDansLaPieceException, PorteFermeeException, VivantAbsentDeLaPieceException{
        porte.activer();
        assertThat(piece.contientVivant(vt), Is.is(true));
        assertThat(autre_piece.contientVivant(vt), Is.is(false));
        vt.franchir(porte);
        assertThat(piece.contientVivant(vt), Is.is(false));
        assertThat(autre_piece.contientVivant(vt), Is.is(true));

    }

    @Test(expected = PorteFermeeException.class)
    public void testPorteFermeeException() throws PorteInexistanteDansLaPieceException, PorteFermeeException, VivantAbsentDeLaPieceException{
        vt.franchir(porte);
    }

    @Test(expected = PorteInexistanteDansLaPieceException.class)
    public void testPorteInexistanteDansLaPieceException() throws NomDEntiteDejaUtiliseDansLeMondeException, PorteInexistanteDansLaPieceException, PorteFermeeException, VivantAbsentDeLaPieceException{
        autre_piece2 = new Piece("2", monde);
        porte2 = new Porte("porte2", monde, autre_piece, autre_piece2);
        vt.franchir(porte2);
    }
}

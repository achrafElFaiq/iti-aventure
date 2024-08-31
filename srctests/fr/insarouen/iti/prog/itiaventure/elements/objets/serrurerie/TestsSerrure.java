package fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsEqual;

import fr.insarouen.iti.prog.itiaventure.Monde;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetTest;
import fr.insarouen.iti.prog.itiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.itiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Piece;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.VivantTest;
import fr.insarouen.iti.prog.itiaventure.elements.structure.Porte;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;

public class TestsSerrure{
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
    Serrure serrure;
    Clef clef;

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
        serrure = new Serrure("Serrure",monde);
        clef = serrure.creerClef();
    }

    @Test
    public void testCreerClef() throws NomDEntiteDejaUtiliseDansLeMondeException{
        assertThat(serrure.creerClef().getNom(),IsEqual.equalTo("clef Serrure"));
    }

    @Test 
    public void testActivableAvec(){
        assertThat(serrure.activableAvec(clef),Is.is(true));
        assertThat(serrure.activableAvec(pdb),Is.is(false));
    }

    @Test (expected = ActivationImpossibleException.class)
    public void testActiver() throws ActivationImpossibleException{
        serrure.activer();
    }

    @Test
    public void testActiverAvec()throws ActivationImpossibleAvecObjetException{
        assertThat(serrure.getEtat()== Etat.DEVEROUILLE, Is.is(true));
        serrure.activerAvec(clef);
        assertThat(serrure.getEtat()== Etat.VERROUILLE, Is.is(true));
    }

    @Test(expected = ActivationImpossibleAvecObjetException.class)
    public void testActiverAvecException() throws ActivationImpossibleAvecObjetException{
        serrure.activerAvec(pdb);
    }

    
}
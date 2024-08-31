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
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;


public class TestMonstre {
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
    Monstre monstre;
    Serrure serrure;

    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException{
         monde = new Monde("Monde test");
        autre_monde = new Monde("Autre monde");
        piece = new Piece("Une piece", monde);
        autre_piece = new Piece("Une autre piece", monde);
        serrure = new Serrure("Serrure de porte",monde);
        porte = new Porte("porte", monde,serrure, piece, autre_piece);
        pdb = new PiedDeBiche("pdb", monde);
        obj = new PiedDeBiche("truc", monde);
        monstre = new Monstre("Prototype", monde, 10, 5, piece, pdb);
    }

    @Test 
    public void testExecuter() throws Throwable{
        autre_piece.deposer(obj);
        assertThat(piece.getObjets().isEmpty(), Is.is(true));
        assertThat(autre_piece.getObjets().containsValue(obj), Is.is(true));
        assertThat(monstre.getObjets().containsValue(pdb), Is.is(true));
        assertThat(monstre.getPiece(), Is.is(piece));
        assertThat(autre_piece.contientVivant(monstre), Is.is(false));
        monstre.executer();
        assertThat(monstre.getpointVie()==9, Is.is(true));
        assertThat(monstre.getPiece(), Is.is(autre_piece));
        assertThat(autre_piece.getObjets().containsValue(pdb), Is.is(true));
        assertThat(autre_piece.getObjets().containsValue(obj), Is.is(false));
        assertThat(monstre.getObjets().containsValue(obj), Is.is(true));
        assertThat(monstre.getObjets().containsValue(pdb), Is.is(false));
        assertThat(autre_piece.contientVivant(monstre), Is.is(true));
        porte.activerAvec(porte.getSerrure().creerClef());
        monstre.executer();
        assertThat(autre_piece.contientVivant(monstre), Is.is(true));
        assertThat(piece.contientVivant(monstre), Is.is(false));
    }
}
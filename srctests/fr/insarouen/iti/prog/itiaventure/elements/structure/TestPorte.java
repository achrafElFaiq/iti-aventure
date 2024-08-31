package fr.insarouen.iti.prog.itiaventure.elements.structure;
import org.junit.Test;
import org.junit.Before;
import fr.insarouen.iti.prog.itiaventure.elements.objets.ObjetTest;
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
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteFermeeException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.itiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.itiaventure.elements.Etat;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.Clef;


public class TestPorte{
    
    Piece piece;
    Piece pieceB;
    Monde insa;
    PiedDeBiche ordi;
    Vivant vivant;
    Porte porte;
    Serrure serrure;
    Clef clef;

    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException{
        insa = new Monde("INSA");
        piece = new Piece("Salle TP", insa);
        pieceB = new Piece("Salle TD", insa);
        ordi = new PiedDeBiche("ordi", insa);
        vivant = new VivantTest("Elouen",insa,10,10,piece);
        serrure = new Serrure("Serrure de porte",insa);
        porte = new Porte("porte", insa,serrure,piece,pieceB);
        clef = serrure.creerClef();
    }


    @Test
    public void testActiver() throws ActivationImpossibleException{
        assertThat(porte.getEtat()== Etat.FERME, Is.is(true));
        porte.activer();
        assertThat(porte.getEtat()== Etat.OUVERT, Is.is(true));
    }

    @Test (expected = ActivationImpossibleException.class)
    public void testActiverException()throws ActivationImpossibleException ,ActivationImpossibleAvecObjetException{
        porte.activerAvec(clef);
        assertThat(porte.getEtat()== Etat.VERROUILLE, Is.is(true));
        porte.activer();
    }

    @Test (expected = ActivationImpossibleAvecObjetException.class)
    public void testActiverAvecException() throws ActivationImpossibleAvecObjetException ,NomDEntiteDejaUtiliseDansLeMondeException{
        porte.activerAvec(new ObjetTest("objet",insa));
    }

    @Test
    public void testActiverAvecPDB() throws ActivationImpossibleAvecObjetException {
        assertThat(porte.getEtat()== Etat.FERME, Is.is(true));
        porte.activerAvec(ordi);
        assertThat(porte.getEtat()== Etat.CASSE, Is.is(true));
    }

    @Test
    public void testGetPieceAutreCote(){
        assertThat(porte.getPieceAutreCote(piece),Is.is(pieceB));
    }


}
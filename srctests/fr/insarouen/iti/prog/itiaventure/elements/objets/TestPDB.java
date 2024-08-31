package fr.insarouen.iti.prog.itiaventure.elements.objets;

import org.junit.Test;
import org.junit.Before;
import fr.insarouen.iti.prog.itiaventure.Monde;
import static org.hamcrest.MatcherAssert.assertThat;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import org.hamcrest.core.Is;

public class TestPDB{
    PiedDeBiche pdb;
    Monde ITI;

    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException{
        ITI = new Monde("ITI");
        pdb = new PiedDeBiche("pdb", ITI);
    }

    @Test
    public void testEstDeplacable(){
        assertThat(pdb.estDeplacable(), Is.is(true));
    }
}
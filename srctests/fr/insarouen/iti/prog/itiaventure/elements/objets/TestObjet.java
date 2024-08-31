package fr.insarouen.iti.prog.itiaventure.elements.objets;

import org.junit.Test;
import org.junit.Before;
import fr.insarouen.iti.prog.itiaventure.Monde;
import static org.hamcrest.MatcherAssert.assertThat;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import org.hamcrest.core.Is;

public class TestObjet{
    ObjetTest obj;
    Monde ITI;

    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException{
        ITI = new Monde("ITI");
        obj = new ObjetTest("Ordinateur", ITI);
    }

    @Test
    public void testEstDeplacable(){
        assertThat(obj.estDeplacable(), Is.is(false));
    }
}
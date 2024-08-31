package fr.insarouen.iti.prog.itiaventure.elements;

import org.junit.Test;
import org.junit.Before;
import org.hamcrest.core.IsEqual;
import fr.insarouen.iti.prog.itiaventure.Monde;
import static org.hamcrest.MatcherAssert.assertThat;
import fr.insarouen.iti.prog.itiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.Is;


public class TestEntite {
    public Monde monde;
    public Monde monde1;
    public EntiteTest entite1;
    public EntiteTest entite;

    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException{
        monde = new Monde("INSA");
        monde1 = new Monde("Pas INSA");
        entite = new EntiteTest("prof", monde);
        entite1 = new EntiteTest("prof1", monde);
    }

    @Test 
    public void testConstructeur(){
        assertThat(entite.getNom(),IsEqual.equalTo("prof"));
        assertThat(entite.getMonde(),Is.is(monde));
        assertThat(entite.getNom(),Is.is(IsNot.not(IsEqual.equalTo("pas prof"))));
        assertThat(entite.getMonde(),Is.is(IsNot.not(monde1)));
    }

    @Test
    public void testGetNom(){
        assertThat(entite.getNom(), IsEqual.equalTo("prof"));
        assertThat(entite.getNom(), Is.is(IsNot.not(IsEqual.equalTo("Pas prof"))));
    }

    @Test 
    public void testGetMonde(){
        assertThat(entite.getMonde(),Is.is(monde));
        assertThat(entite.getMonde(),Is.is(IsNot.not(monde1)));
    }

    @Test
    public void testEqual(){
        assertThat(entite.equals(entite),Is.is(true));
        assertThat(entite.equals(entite1),Is.is(false));
    }


}
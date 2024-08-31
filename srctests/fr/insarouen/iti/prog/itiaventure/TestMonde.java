package fr.insarouen.iti.prog.itiaventure;

import org.junit.Test;
import org.junit.Before;
import org.hamcrest.core.IsEqual;
import fr.insarouen.iti.prog.itiaventure.elements.EntiteTest;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;

public class TestMonde{
    Monde monde1;
    Monde monde2;
    EntiteTest entite;
    EntiteTest entite1;
    


    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException{
        monde1= new Monde("INSA");
        monde2= new Monde ("ESIGELEC");
        entite= new EntiteTest("Malandain" , monde1);
        entite1 = new EntiteTest("Geraldine",monde2);
    }

    @Test
    public void testConstructeur(){
        assertThat(monde1.getNom(), IsEqual.equalTo("INSA"));
        assertThat(monde1.getNom(), IsNot.not(IsEqual.equalTo(monde2.getNom())));
    }

    @Test
    public void testGetNom(){
        assertThat(monde1.getNom(), IsEqual.equalTo("INSA"));
        assertThat(monde1.getNom(), IsNot.not(IsEqual.equalTo(monde2.getNom())));
    }

    @Test
    public void testGetEntite(){
        assertThat(monde1.getEntite("Malandain"),Is.is(entite));
        assertThat(monde1.getEntite("Malandain"),IsNot.not(entite1));
        assertThat(monde1.getEntite("Geraldine"),IsNull.nullValue());
    }

    @Test 
    public void testAjouter() throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException{
        assertThat(monde1.getEntite("Geraldine"),IsNull.nullValue());
        EntiteTest entite2 = new EntiteTest("Geraldine",monde1);
        assertThat(monde1.getEntite("Geraldine"),Is.is(entite2));
    }

    @Test (expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void testNomDEntiteDejaUtiliseDansLeMondeException() throws NomDEntiteDejaUtiliseDansLeMondeException{
        EntiteTest entite4 = new EntiteTest("Malandain" , monde1);
    }

    @Test( expected = EntiteDejaDansUnAutreMondeException.class)
    public void testEntiteDejaDansUnAutreMondeException() throws EntiteDejaDansUnAutreMondeException, NomDEntiteDejaUtiliseDansLeMondeException{
        monde1.ajouter(entite1);
    }




}

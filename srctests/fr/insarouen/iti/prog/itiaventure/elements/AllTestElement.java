package fr.insarouen.iti.prog.itiaventure.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import fr.insarouen.iti.prog.itiaventure.elements.objets.AllTestsObjet;
import fr.insarouen.iti.prog.itiaventure.elements.structure.AllTestsStructure;
import fr.insarouen.iti.prog.itiaventure.elements.vivants.AllTestsVivant;

@RunWith(Suite.class)

@SuiteClasses({
    TestEntite.class,
    AllTestsObjet.class, 
    AllTestsStructure.class, 
    AllTestsVivant.class
})

public class AllTestElement{}
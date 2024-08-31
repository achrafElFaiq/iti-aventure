package fr.insarouen.iti.prog.itiaventure.elements.objets;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import fr.insarouen.iti.prog.itiaventure.elements.objets.serrurerie.AllTestsSerrurerie;


@RunWith(Suite.class)

@SuiteClasses({
    TestObjet.class,
    TestPDB.class,
    AllTestsSerrurerie.class
})

public class  AllTestsObjet{}

package fr.insarouen.iti.prog.itiaventure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import fr.insarouen.iti.prog.itiaventure.elements.AllTestElement;

@RunWith(Suite.class)

@SuiteClasses({
    AllTestElement.class,
    TestMonde.class
})

public class AllTests {}

//$(find ./src -name *.java)
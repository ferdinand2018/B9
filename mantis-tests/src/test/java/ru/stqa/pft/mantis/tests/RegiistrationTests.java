package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegiistrationTests extends TestBase{
    @Test
    public void testRegistration(){
        app.registration().start("", "");
    }
}

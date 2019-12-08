package ru.stqa.pft.addressbook.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactRemoveFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Иван").withLastname("Иванов"), true);
        }
    }
    @Test
    public void testUntitledTestCase() throws Exception {
        Contacts before = app.db().contacts();
        app.appremove().changeGroupFilter();
        app.appremove().selectChangeGroupFilter();
        app.appremove().changeGroup();
        app.appremove().deleteContact();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
    }
}

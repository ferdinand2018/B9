package ru.stqa.pft.addressbook.tests;

import java.util.Set;
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
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Иван").withLastname("Иванов"), true);
        }
        if(app.db().groups().size() == 0) {
            app.goTo().homePage();
            app.group().create(new GroupData().withName("test1"));
        }
    }
    @Test
    public void testUntitledTestCase() throws Exception {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData deletedUser = before.iterator().next();
        ContactData contact = new ContactData().withId(deletedUser.getId()).inGroup(groups.iterator().next());
        if (contact.getGroups().size() == 0) {
            app.appremove().addToGroup(contact);
        }
        Set<GroupData> userGroups = (Set<GroupData>) contact.getGroups();
        GroupData groupToDel = userGroups.iterator().next();
        GroupData group = new GroupData().withId(groupToDel.getId());
        app.appremove().deleteFromGroup(contact, group);
        /*Contacts before = app.db().contacts();
        app.appremove().changeGroupFilter();
        app.appremove().selectChangeGroupFilter();
        app.appremove().changeGroup();
        app.appremove().deleteContact();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());*/
    }
}

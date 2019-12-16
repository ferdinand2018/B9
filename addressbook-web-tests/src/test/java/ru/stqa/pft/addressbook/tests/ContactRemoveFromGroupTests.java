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
    public void testRemoveContacFromGroup() throws Exception {
        Groups groups = app.db().groups();
        Contacts beforeContact = app.db().contacts();
        ContactData deletedContact = beforeContact.iterator().next();
        ContactData contact = new ContactData().withId(deletedContact.getId()).inGroup(groups.iterator().next());
        for (ContactData delContact : beforeContact) {
            Groups contactInGroup = delContact.getGroups();
            if (contactInGroup.size() != 0) {
                deletedContact = delContact;
                break;
            }
        }
        if (deletedContact.getGroups().size() == 0) {
            GroupData groupToAdd = groups.iterator().next();
            app.appremove().addToGroup(deletedContact, groupToAdd);
        }
        int beforeContactId = deletedContact.getId();
        Contacts allContacts = app.db().contacts();
        ContactData contactBefore = null;
        for (ContactData contactBefore1 : allContacts) {
            if (contactBefore1.getId() == beforeContactId) {
                contactBefore = contactBefore1;
                break;
            }
        }
        Groups before = deletedContact.getGroups();
        GroupData grouptoremove = before.iterator().next();
        app.appremove().deleteFromGroup(deletedContact, grouptoremove);

        Contacts allContactsAfter = app.db().contacts();
        ContactData contactAfter = null;

        for(ContactData contactAfter1 : allContactsAfter){
            if (contactAfter1.getId() == beforeContactId){
                contactAfter = contactAfter1;
                break;
            }
        }
        assert (contactBefore.equals(contactAfter));
    }
}

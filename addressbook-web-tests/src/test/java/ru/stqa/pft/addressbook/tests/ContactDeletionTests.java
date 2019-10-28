package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreationPage();
            app.getContactHelper().createContact(new ContactData("firstname", "middlename", "lastname", "123", null, "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptDelete();
    }
}

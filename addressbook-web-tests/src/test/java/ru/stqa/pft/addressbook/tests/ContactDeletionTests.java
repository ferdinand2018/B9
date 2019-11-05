package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoHomePage();
        ContactData contact = new ContactData("Иван", "Иванович", "Иванов", null, null, "test1");
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(contact, true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact(1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptDelete();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

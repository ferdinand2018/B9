package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        ContactData contact = new ContactData("Иван", "Иванович", "Иванов", null, null, "test1");
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(contact, true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size());
        ContactData contact1 = new ContactData("Иван", "Иванович", "Иванов", null, null, null);
        app.getContactHelper().fillContactForm(contact1, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

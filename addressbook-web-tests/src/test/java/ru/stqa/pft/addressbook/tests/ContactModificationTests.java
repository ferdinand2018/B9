package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if(!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreationPage();
            app.getContactHelper().createContact(new ContactData("firstname", "middlename", "lastname", "123", null, "test1"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("firstname", "middlename", "lastname", "123", "321", null), false); //Заполнение данных о контакте
        app.getContactHelper().submitContactModification();
    }
}

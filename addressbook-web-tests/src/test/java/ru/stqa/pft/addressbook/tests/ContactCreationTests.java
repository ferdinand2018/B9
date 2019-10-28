package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoContactCreationPage(); //Переход на страницу создания контактов
        app.getContactHelper().fillContactForm(new ContactData("firstname", "middlename", "lastname", "123", null, "test1"), true); //Заполнение данных о контакте
        app.getContactHelper().submitContactCreation(); //Отправка данных
        app.getContactHelper().returnToContactPage(); //Переход на страницу контактов
        app.logout(); //Выход из системы
    }
}

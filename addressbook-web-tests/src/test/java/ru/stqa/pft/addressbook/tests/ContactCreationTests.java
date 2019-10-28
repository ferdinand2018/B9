package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{
    @Test
    public void testContactCreation() throws Exception {
        app.gotoContactCreationPage(); //Переход на страницу создания контактов
        app.fillContactForm(new ContactData("firstname", "middlename", "lastname", "123", "321")); //Заполнение данных о контакте
        app.submitContactCreation(); //Отправка данных
        app.returnToContactPage(); //Переход на страницу контактов
        app.logout(); //Выход из системы
    }
}

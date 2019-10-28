package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

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

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AppRemoveHelper extends HelperBase{
    private boolean acceptNextAlert = true;

    public AppRemoveHelper(WebDriver wd) {
        super(wd);
    }

    public void selectContactById(){
        click(By.id("122"));
    }

    public void selectGroup(){
        click(By.name("to_group"));
    }

    public void addToGroup(){
        click(By.name("add"));
    }

    public void returnToContactPage(){
        click(By.linkText("group page \"test1\""));
    }

    public void changeGroupFilter(){
        click(By.name("group"));
    }

    public void selectChangeGroupFilter(){
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("test1");
    }

    public void changeGroup(){
        click(By.name("group"));
    }

    public void deleteContact(){
        acceptNextAlert = true;
        click(By.xpath("//input[@value='Delete']"));
        //assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void gotoHome(){
        click(By.linkText("home"));
    }
}

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

    }
}

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AppRemoveHelper extends HelperBase{
    private boolean acceptNextAlert = true;

    public AppRemoveHelper(WebDriver wd) {
        super(wd);
    }

    public void selectContactById(int id){
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void selectGroup(){
        click(By.name("to_group"));
    }

    public void addToGroup(ContactData contact, GroupData groupAdd){
        selectContactById(contact.getId());
        addSelectedContactToGroup(contact, groupAdd);
    }

    private void addSelectedContactToGroup(ContactData contact, GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contact.getGroups().iterator().next().getName());
        click(By.xpath("//input[@value='Add to']"));
        wd.findElement(By.cssSelector("div.msgbox"));
        System.out.println("Added user " + contact.getId() + " to a group " + group.getId() + " " + group.getName());
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

    public void deleteContact(ContactData contact, GroupData group){
        new Select(wd.findElement(By.name("group"))).selectByIndex(group.getId());
        selectContactById(contact.getId());
        deleteSelectedContactFromGroup(contact, group);
    }

    private void deleteSelectedContactFromGroup(ContactData contact, GroupData group) {
        click(By.xpath("//input[@name='remove']"));
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void gotoHome(){
        click(By.linkText("home"));
    }
    public void deleteFromGroup(ContactData contact, GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByValue(group.getName());
        selectContactById(contact.getId());
        deleteSelectedContactFromGroup(contact, group);
    }
}

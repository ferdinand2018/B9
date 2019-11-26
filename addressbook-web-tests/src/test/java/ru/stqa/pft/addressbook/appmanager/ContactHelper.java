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

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactPage() {
        //click(By.linkText("home page"));
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.xpath("//input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());

        if(creation){
            /*new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());*/
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index){
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id){
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//div[2]/input"));
    }

    public void acceptDelete() {
        wd.switchTo().alert().accept();
    }

    public int initContactModification(int i) {
        click(By.xpath("//a[@href='edit.php?id=" + i + "']"));
        return i;
    }

    public void deleteSelectedContacts(){
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void create(ContactData contact, boolean b) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToContactPage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContacts();
        acceptDelete();
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        acceptDelete();
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        //selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToContactPage();
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname));
        }
        return contacts;
    }

    public Set<ContactData> allMerge() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhone = cells.get(5).getText();
            String allEmail = cells.get(4).getText();
            String address = cells.get(3).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withLastname(lastname)
                    .withFirstname(firstname)
                    .withAllPhones(allPhone)
                    .withAllEmails(allEmail)
                    .withAddress(address));
        }
        return contacts;
    }

    public ContactData  infoFromEditForm(ContactData contact){
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId()).withLastname(lastname).withFirstname(firstname)
                .withHome(home).withMobile(mobile).withWork(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).click();
        //wd.findElement(By.xpath("//input[value = '%s']/../../td[8]/a")).click();
    }
}

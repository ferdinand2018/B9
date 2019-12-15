package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class ContactAddToGroupTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Иван").withLastname("Иванов"), true);
    }
    if(app.db().groups().size() == 0) {
      app.goTo().homePage();
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testContactAddToGroup() throws Exception {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    ContactData addContact = before.iterator().next();
    ContactData contact = new ContactData().withId(addContact.getId()).inGroup(groups.iterator().next());
    if (contact.getGroups().size() != app.db().groups().size()) {
      app.appremove().addToGroup(contact);
      Contacts after = app.db().contacts();
      assertEquals(after.size(), before.size());
    }
  }
}

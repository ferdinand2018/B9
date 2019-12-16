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
  private GroupData groupAdd;
  private ContactData contactAdd;
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
    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();
    groupAdd = null;

    for (ContactData contactToAdd1 : allContacts) {
      Groups contactGroups = contactToAdd1.getGroups();
      if(contactGroups.size()!= allGroups.size()) {
        allGroups.removeAll(contactGroups);
        groupAdd = allGroups.iterator().next();
        contactAdd = contactToAdd1;
        break;
      }
    }
    if(groupAdd==null){
      ContactData newContact = new ContactData().withFirstname("Иван").withLastname("Иванов");
      app.contact().create(newContact, false);
      Contacts after = app.db().contacts();
      newContact.withId(after.stream().mapToInt((g) -> (g).getId()).max().getAsInt());
      contactAdd = newContact;
      groupAdd = allGroups.iterator().next();
    }
  }
  @Test
  public void testContactAddToGroup() throws Exception {
    app.goTo().homePage();
    ContactData contactBefore = contactAdd;
    Groups contactgGoupsBefore = contactAdd.getGroups();
    int beforeid = contactAdd.getId();

    app.appremove().addToGroup(contactAdd, groupAdd);

    Contacts allusersafter = app.db().contacts();
    ContactData contactAfter = null;

    for(ContactData userafter1 : allusersafter){
      if (userafter1.getId() == beforeid){
        contactAfter = userafter1;
        break;
      }
    }
    assert (contactgGoupsBefore.equals(contactAfter.getGroups().withOut(groupAdd)));
    assert (contactBefore.equals(contactAfter));
  }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactAddToGroupTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Иван").withLastname("Иванов"), true);
    }
  }
  @Test
  public void testContactAddToGroup() throws Exception {
    Contacts before = app.db().contacts();
    app.appremove().selectContactById();
    app.appremove().selectGroup();
    app.appremove().addToGroup();
    app.appremove().returnToContactPage();
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size());
  }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactAddToGroupTests extends TestBase{
  @Test
  public void testContactAddToGroup() throws Exception {
    app.appremove().selectContactById();
    app.appremove().selectGroup();
    app.appremove().addToGroup();
    app.appremove().returnToContactPage();
  }
}

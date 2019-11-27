package ru.stqa.pft.addressbook.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactRemoveFromGroupTests extends TestBase {
    @Test
    public void testUntitledTestCase() throws Exception {
        app.appremove().changeGroupFilter();
        app.appremove().selectChangeGroupFilter();
        app.appremove().changeGroup();
        app.appremove().deleteContact();
    }
}

package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import com.google.protobuf.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import java.io.File;
import java.io.IOException;

public class TestBase {
    protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    boolean isIssueNotFixed(int issueId) throws IOException, ServiceException, javax.xml.rpc.ServiceException {
        Issue testIssue = app.soap().getIssue(issueId);
        ObjectRef issuestatus = testIssue.getStatus();
        if(issuestatus != null){
            return true;
        }else {
            return false;
        }
    }

    boolean isIssueOpen(int issueId) throws IOException, ServiceException, javax.xml.rpc.ServiceException {
        Issue testIssue = app.soap().getIssue(issueId);
        ObjectRef issuestatus = testIssue.getStatus();
        if (issuestatus == null) {
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException, ServiceException, javax.xml.rpc.ServiceException {
        if (isIssueNotFixed(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId + " is not fixed");
        }
    }

    public void skipIfOpen(int issueId) throws IOException, ServiceException, javax.xml.rpc.ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId + " is open");
        }
    }

}

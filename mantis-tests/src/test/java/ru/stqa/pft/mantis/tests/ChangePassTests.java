package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.DbHelper;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePassTests extends TestBase{
    public void startMailServer(){
        app.mail().start();
    }
    @Test
    public void testLogin() throws IOException, MessagingException, InterruptedException {
        UserData user =  DbHelper.testDbConnection();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = "password";
        String newpassword = "newpassword";
        String adminname = app.getProperty("web.adminLogin");
        String adminpwd = app.getProperty("web.adminPassword");
        HttpSession session = app.newSession();
        app.changePass().admLogin(adminname, adminpwd);
        app.changePass().gotoReset();
        app.changePass().selectUser(username);
        app.james().doesUserExist(username);
        app.changePass().resetPassword();
        app.changePass().logout();
        List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        System.out.println(confirmationLink);
        app.changePass().finishPwdReset(confirmationLink, newpassword, username);
        app.changePass().loginAsUser(username, newpassword);
        assertTrue(session.isLoggedInAs(username));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex()
                .find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}

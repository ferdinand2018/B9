package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class ChangePassHelper extends HelperBase{
    public ChangePassHelper(ApplicationManager app){
        super(app);
    }

    public void admLogin(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.xpath("//input[@name='username']"), username);
        type(By.xpath("//input[@name='password']"), password);
        click(By.xpath("//input[@type='submit']"));
    }

    public void gotoReset() throws InterruptedException {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }


    public void selectUser(String username) {
        click(By.xpath("//a[.="+ username + "]"));
    }

    public void resetPassword() {
        click(By.xpath("//input[@value=\"Reset Password\"]"));
    }

    public void logout() {
        click(By.xpath("//a[.=\"Logout\"]"));
    }

    public void finishPwdReset(String confirmationLink, String newpassword, String username) {
        wd.get(confirmationLink);
        type(By.name("username"), username);
        type(By.name("password"), newpassword);
        click(By.xpath("//input[@type='submit']"));
    }

    public void loginAsUser(String name, String password) throws IOException {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), name);
        type(By.name("password"), password);
        click(By.xpath("//input[@type='submit']"));
    }
}

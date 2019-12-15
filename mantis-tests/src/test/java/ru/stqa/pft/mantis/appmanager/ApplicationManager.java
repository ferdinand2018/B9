package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private FtpHelper ftp;
    private String broweser;
    private RegistrationHelper registrationHelper;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private ChangePassHelper changePassHelper;
    private DbHelper dbHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(String broweser) {
        this.broweser = broweser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if(wd != null){
            wd.quit();
        }
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if(registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public ChangePassHelper changePass(){
        if(changePassHelper == null){
            changePassHelper = new ChangePassHelper(this);
        }
        return changePassHelper;
    }

    public FtpHelper ftp(){
        if(ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if(wd == null){
            if(broweser.equals(BrowserType.FIREFOX)){
                //wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
                wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:/Program Files (x86)/Mozilla Firefox ESR/firefox.exe"));
            } else if(broweser.equals(BrowserType.CHROME)){
                wd = new ChromeDriver();
            } else if(broweser.equals(BrowserType.IE)){
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail(){
        if(mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james(){
        if(jamesHelper == null){
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public DbHelper db(){
        return dbHelper;
    }

    public SoapHelper soap(){
        if (soapHelper == null){
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}

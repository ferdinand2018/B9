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
    WebDriver wd;

    private String broweser;

    public ApplicationManager(String broweser) {
        this.broweser = broweser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

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

    public void stop() {
        wd.quit();
    }
}

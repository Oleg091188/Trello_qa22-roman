package com.telran.trello.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;

    BoardHelper board;
    TeamHelper team;
    HeaderHelper header;
    SessionHelper session;


    public void init() {
        String browser = System.getProperty("browser", BrowserType.CHROME);
        if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.EDGE)) {
            wd = new EdgeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        maximize();
        wd.get("https://trello.com/");
        session = new SessionHelper(wd);
        header = new HeaderHelper(wd);
        board = new BoardHelper(wd);
        team = new TeamHelper(wd);

    }

    public void stop() {
        wd.quit();
    }

    public void maximize() {
        wd.manage().window().maximize();
    }

    public BoardHelper getBoard() {
        return board;
    }

    public TeamHelper getTeam() {
        return team;
    }

    public HeaderHelper getHeader() {
        return header;
    }

    public SessionHelper getSession() {
        return session;
    }
}

package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    EventFiringWebDriver wd;
    HelperUser helperUser;
    HelperCar car;
    HelperSearch search;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);




    public void init(){
        wd = new EventFiringWebDriver(new ChromeDriver());
        logger.info("All tests start in ChromeDriver");
        logger.info("All tests start in 'ChromeDriver'");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.xyz/search");
        helperUser = new HelperUser(wd);
        car = new HelperCar(wd);
        search = new HelperSearch(wd);
        wd.register(new MyListener());
    }

    public void stop(){
        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar car() {
        return car;
    }

    public HelperSearch search() {
        return search;
    }
}

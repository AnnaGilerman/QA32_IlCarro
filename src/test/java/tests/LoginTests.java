package tests;

import manager.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod

    public void preCondition(){

        if(app.getHelperUser().isLogOutPresent()){
            app.getHelperUser().logout();
            logger.info("Test needs logout");
        }

    }

    @Test

    public void loginSuccess(){
       // logger.info("Start test LoginSuccess");

        logger.info("The test starts with data [alexania1102@gmail.com] & [1q2w3e4R]");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alexania1102@gmail.com", "1q2w3e4R");
        logger.info("fill form");
        app.getHelperUser().submit();
        app.getHelperUser().pause(1000);
        Assert.assertEquals(app.getHelperUser().checkMessage(), "Logged in success");
        logger.info("Test passed");

    }

    @Test(dataProvider = "validLoginData", dataProviderClass = MyDataProvider.class)

    public void loginSuccessDataProvider(User user){
        // logger.info("Start test LoginSuccess");

        logger.info("The test starts with data [alexania1102@gmail.com] & [1q2w3e4R]");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        logger.info("fill form");
        app.getHelperUser().submit();
        app.getHelperUser().pause(1000);
        Assert.assertEquals(app.getHelperUser().checkMessage(), "Logged in success");
        logger.info("Test passed");

    }


    @Test

    public void loginSuccessModel(){

        User user = new User().withEmail("alexania1102@gmail.com").withPassword("1q2w3e4R");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        app.getHelperUser().pause(1000);
        Assert.assertEquals(app.getHelperUser().checkMessage(), "Logged in success");


    }

    @Test

    public void loginSuccessNew(){

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("alexania1102@gmail.com", "1q2w3e4R");
        app.getHelperUser().submit();
        app.getHelperUser().pause(1000);
        Assert.assertEquals(app.getHelperUser().checkMessage(), "Logged in success");


    }

    @AfterMethod

    public void postCondition(){
        app.getHelperUser().submitOkButton();
    }
}

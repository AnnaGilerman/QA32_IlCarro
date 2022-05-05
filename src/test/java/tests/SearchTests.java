package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{
    @BeforeMethod

    public void preCondition(){
        //if loginBTN -----> login
        if(!app.getHelperUser().isLogOutPresent()){
            User user = new User().withEmail("alexania1102@gmail.com").withPassword("1q2w3e4R");
            app.getHelperUser().login(user);
            logger.info("Test start with user ---->" + user.toString());
        }
    }
    @Test
    public void searchCurrentMonth(){
        app.search().searchCurrentMonth("Tel Aviv", "04/10/2022", "04/20/2022");
        app.search().submit();
      Assert.assertTrue(app.search().isListOfCarsAppeared());

    }


    @Test
    public void searchPeriodInPast(){
        app.search().searchPeriodInPast("Tel Aviv", "02/01/2022", "04/20/2022");
        app.search().submitWithoutWait();

        Assert.assertFalse(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertTrue(app.search().isPeriodInPast());

    }
    @Test
    public void searchAnyPeriod(){
        app.search().searchAnyPeriod("Tel Aviv", "01/05/2023", "03/15/2023");
        app.search().submit();
        Assert.assertTrue(app.search().isListOfCarsAppeared());
    }



    @AfterMethod
    public void postCondition(){
        app.search().returnToHomePage();

    }
}

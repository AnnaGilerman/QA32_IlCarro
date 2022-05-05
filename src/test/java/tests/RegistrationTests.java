package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegistrationTests extends TestBase{

    @BeforeMethod

    public void preCondition(){

        if(app.getHelperUser().isLogOutPresent()){
            app.getHelperUser().logout();
        }

    }

    @Test

    public void registrationSuccess(){
        int index = (int)(System.currentTimeMillis())/1000%36000;

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm("Alex", "Portnoy", "alex"+index+"@gmail.com", "Aalex12345$");
        //app.getHelperUser().checkPolicy();
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
       // app.getHelperUser().pause(1000);
        assertEquals(app.getHelperUser().checkMessage(), "You are logged in success");
    }

    @Test

    public void registrationSuccessModel(){
        int index = (int)(System.currentTimeMillis())/1000%36000;
        User user = new User().withName("Alex").withLastName("Portnoy").withEmail("alex"+index+"@gmail.com").withPassword("Aalex12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        //app.getHelperUser().checkPolicy();
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
      //  app.getHelperUser().pause(1000);
        assertEquals(app.getHelperUser().checkMessage(), "You are logged in success");
    }
    @Test

    public void registrationWrongPasswordModel(){
        User user = new User().withName("Alex").withLastName("Portnoy").withEmail("alex@gmail.com").withPassword("12345");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        //app.getHelperUser().checkPolicy();
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isErrorPasswordDisplayedSize());
        Assert.assertTrue(app.getHelperUser().isErrorPasswordDisplayedFormat());
        Assert.assertFalse(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotClickable());
    }

    @AfterMethod

    public void postCondition(){
        app.getHelperUser().submitOkButton();
    }
}

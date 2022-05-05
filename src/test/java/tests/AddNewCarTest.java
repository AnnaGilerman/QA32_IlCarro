package tests;

import manager.MyDataProvider;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarTest extends TestBase{

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

    public void addNewCarSuccess(){
        int index = (int)(System.currentTimeMillis())/1000%36000;
        logger.info("'Car Reg Number' 100-22-" + index);

        Car car = Car.builder()
                .address("Tel Aviv, Israel")
                .make("BMW")
                .model("M5")
                .year("2022")
                .engine("2.5")
                .fuel("Petrol")
                .gear("MT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .clasS("C")
                .fuelConsumption("6.5")
                .carRegNumber("100-22-" + index)
                .price("65")
                .distanceIncluded("500")
                .feature("Type of features")
                .about("Very good car")
                .build();
        logger.info("Add car---->" + car.toString());
        app.car().openCarForm();
        app.car().fillCarForm(car);
        app.car().attachPhoto("C://COMP//QA 32//Automation//QA32_IlCarro//bmw-zseries-z4-modelfinder-stage2-890x501.png");
        app.car().submit();

        Assert.assertTrue(app.car().isCarAdded());


    }

    @Test(dataProvider = "validDataCarCSV", dataProviderClass = MyDataProvider.class)

    public void addNewCarSuccessDataProviderCSV(Car car){
        int index = (int)(System.currentTimeMillis())/1000%36000;
        car.setCarRegNumber("100-22-" + index);

        logger.info("'Car Reg Number' 100-22-" + index);

      //  logger.info("Add car---->" + car.toString());
        app.car().openCarForm();
        app.car().fillCarForm(car);
        app.car().attachPhoto("C://COMP//QA 32//Automation//QA32_IlCarro//bmw-zseries-z4-modelfinder-stage2-890x501.png");
        app.car().submit();

        Assert.assertTrue(app.car().isCarAdded());


    }
}

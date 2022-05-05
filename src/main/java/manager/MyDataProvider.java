package manager;

import models.Car;
import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {

    @DataProvider
    public Iterator<Object[]> validDataCarCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/car.csv")));
        String line = reader.readLine();
        while (line != null){
            String [] split = line.split(";");
            list.add(new Object[]{Car.builder()
                    .address(split[0])
                    .make(split[1])
                    .model(split[2])
                    .year(split[3])
                    .engine(split[4])
                    .fuel(split[5])
                    .gear(split[5])
                    .wD(split[6])
                    .doors(split[7])
                    .seats(split[8])
                    .clasS(split[9])
                    .fuelConsumption(split[10])
                   // .carRegNumber(split[11])
                    .price(split[12])
                    .distanceIncluded(split[13])
                    .feature(split[14])
                    .about(split[15])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validLoginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("alexania1102@gmail.com").withPassword("1q2w3e4R")});
        list.add(new Object[]{new User().withEmail("alexania1102@gmail.com").withPassword("1q2w3e4R")});
        list.add(new Object[]{new User().withEmail("alexania1102@gmail.com").withPassword("1q2w3e4R")});

        return list.iterator();
    }
}

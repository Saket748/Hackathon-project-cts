package org.home;

import org.PagesObject.FiltersPage;
import org.driverSetup.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Home {
    public static void main(String[] args) {
        System.out.println("HI");
        System.out.println("Started");
        WebDriver driver =  Driver.DriverSetup();

    }
}
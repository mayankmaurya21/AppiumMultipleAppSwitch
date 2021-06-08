package sample.OtherApps;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class otherAppsSample {
    //Update with your BrowserStack App Automate Username and Key
    public static String userName = "BS_userName";
    public static String accessKey = "BS_accessKey";

    public static void main(String args[]) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        //Running tests on Google Pixel 2
        caps.setCapability("device", "Google Pixel 2");
        caps.setCapability("build", "MultipleAppsSwtich");
        caps.setCapability("name", "Bstack Sample Test");
        
        //Upload the app to be tested. 
        caps.setCapability("app", "app_url");
        
        //Using otherApps capability to install multiple apps in the same session. 
        caps.setCapability("otherApps", new String[]{"app_url"});

        AndroidDriver driver = new AndroidDriver(new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);
        //Setting implicit wait for 30 seconds
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Check if the apps specified under otherApps capability is installed on the device and add AppPackage. 
        Boolean isInstalled=driver.isAppInstalled("AppPackage");

        if(isInstalled) {
        	
            //Switch to the app specifued under otherApps capability by adding AppPackage & AppActivity.
            Activity welcomeActivity = new Activity("AppPackage", "AppActivity");
            driver.startActivity(welcomeActivity);
            
        }

        //Adding sleep for better video logs
        Thread.sleep(5000);

        //Switch back to the primary app install by adding it's AppPackage & AppActivity.
        Activity WPActivity = new Activity("AppPackage", "AppActivity");
        driver.startActivity(WPActivity);

        //Adding sleep for better video logs
        Thread.sleep(5000);

        //End session
        driver.quit();
    }
}
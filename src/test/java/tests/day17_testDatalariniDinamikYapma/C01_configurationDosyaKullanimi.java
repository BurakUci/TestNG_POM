package tests.day17_testDatalariniDinamikYapma;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import static utilities.Driver.driver;

public class C01_configurationDosyaKullanimi {

    @Test
    public void dinamikPozitifLoginTesti(){

        // 1- https://www.testotomasyonu.com/ anasayfasina gidin

        // Driver.getDriver().get("configuration.properties dosyasina git bana toUrl degerini getir");
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        // 2- account linkine basin
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();
        testotomasyonuPage.accountLinki
                .click();

        // 3- Kullanici email'i olarak gecerli email girin
        testotomasyonuPage.loginSayfasiEmailKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliEmail"));

        // 4- Kullanici sifresi olarak gecerli password girin
        testotomasyonuPage.loginSayfasiPasswordKutusu
                .sendKeys(ConfigReader.getProperty("toGecerliPassword"));

        // 5- Login butonuna basarak login olun
        testotomasyonuPage.loginSayfasiSubmitButonu
                .click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 300 piksel aşağı kaydır
        js.executeScript("window.scrollBy(0, 300);");

        // 6- Basarili olarak giris yapilabildigini test edin
        Assert.assertTrue(testotomasyonuPage.logoutButonu.isDisplayed());


        // 7- logout olun
        testotomasyonuPage.logoutButonu
                .click();

        // 8- sayfayi kapatin
        Driver.quitDriver();

    }
}
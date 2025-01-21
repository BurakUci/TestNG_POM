package tests.day17_testDatalariniDinamikYapma;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TestotomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import static utilities.Driver.driver;

public class C03_AlisverisSepetiTesti {

    @Test (groups = {"smoke","regression"})
    public void alisverisTesti(){
        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));

        //2- belirlenmis arama kelimesi icin arama yapin
        TestotomasyonuPage testotomasyonuPage = new TestotomasyonuPage();

        testotomasyonuPage.aramaKutusu
                .sendKeys( ConfigReader.getProperty("toAranacakKelime") + Keys.ENTER);


        //3- Listelenen sonuclardan ilkini tiklayin
        testotomasyonuPage.bulunanUrunElementleriList
                .get(0)
                .click();
        ReusableMethods.bekle(1);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 500 piksel aşağı kaydır
        js.executeScript("window.scrollBy(0, 1000);");

        //4- urun ismini kaydedin
        String ilkUrunIsmi = testotomasyonuPage.urunSayfasindakiIsimElementi.getText();

        // ve urunu sepete ekleyin
        testotomasyonuPage.urunSayfasindakiSepeteEkleButonu
                .click();

        //5- your cart linkine tiklayin
        testotomasyonuPage.urunSayfasindakiYourCartButonu
                .click();

        //6- kaydettiginiz urun ismi ile sepetteki urun isminin ayni oldugunu test edin

        String sepettekiUrunIsmi = testotomasyonuPage.yourCartSayfasindakiUrunIsmi.getText();

        Assert.assertEquals(ilkUrunIsmi , sepettekiUrunIsmi);

        //7- sayfayi kapatin
        Driver.quitDriver();
    }
}

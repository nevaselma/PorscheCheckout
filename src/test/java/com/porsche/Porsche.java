package com.porsche;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Porsche {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.porsche.com/usa/modelstart/");
		driver.findElement(By.className("b-teaser-preview-wrapper")).click();
		String basePrice = driver.findElement(By.className("m-14-model-price")).getText();
		
		System.out.println(basePrice);

		int basePriceIntCayman = Utility.stringToNumber(basePrice);
		System.out.println(basePriceIntCayman);

		driver.findElement(By.xpath("//*[@id=\"m982120\"]/div[2]/div/a/span")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		String winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		//verify base price
		basePrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
		int basePriceIntCayman2= Utility.stringToNumber(basePrice);
		Utility.priceEquality(basePriceIntCayman,basePriceIntCayman2);
		
		//verify price for equipment is 0
		String priceForEquipment = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		
		int priceForEquipmentInt = Utility.stringToNumber(priceForEquipment);
		System.out.println(priceForEquipmentInt);
		
		if(priceForEquipmentInt == 0) {
			System.out.println("Pass2");
		}else {
			System.out.println("Fail2");
			System.out.println("expected: "+ 0);
			System.out.println("actual: "+ priceForEquipment);
		}
		
		//Delivery fee
		String priceForDelivery = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		int priceForDeliveryInt = Utility.stringToNumber(priceForDelivery);
		
		//Total price
		String totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int totalPriceInt = Utility.stringToNumber(totalPrice);
		
		// Verify that total price is the sum of base price + Delivery, Processing and Handling Fee
		
		Utility.verifyTotalPrice(basePriceIntCayman2, priceForEquipmentInt, priceForDeliveryInt, totalPriceInt);
		
		//Verify that Price for Equipment is Equal to Miami Blue price
		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click();
		String miamiBluePrice = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IAF\"]/div[2]/div[1]/div/div[2]")).getText();
		
		int miamiBluePriceInt = Utility.stringToNumber(miamiBluePrice);
		
		String priceForEquipmentMiami = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int priceForEquipmentMiamiInt = Utility.stringToNumber(priceForEquipmentMiami);
		Utility.priceEquality(miamiBluePriceInt,priceForEquipmentMiamiInt);
		
		//Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
		String totalPriceMiamiBlue = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int totalPriceMiamiBlueInt = Utility.stringToNumber(totalPriceMiamiBlue);
		Utility.verifyTotalPrice(basePriceIntCayman2, priceForEquipmentMiamiInt, priceForDeliveryInt, totalPriceMiamiBlueInt);
		
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
	    Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"submenu_exterieur_x_AA_submenu_x_IRA\"]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
		
		String priceForWheel = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IRA\"]/div[2]/div[1]/div/div[2]")).getText();
		int priceForWheelInt = Utility.stringToNumber(priceForWheel);
		
		String priceForEquipmentWheel = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int priceForEquipmentWheelInt = Utility.stringToNumber(priceForEquipmentWheel);
		int sumOfPrice = priceForEquipmentMiamiInt + priceForWheelInt ;
		Utility.priceEquality(priceForEquipmentWheelInt,sumOfPrice );
		
		//Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
		String totalPriceForWheel = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int totalPriceForWheelInt = Utility.stringToNumber(totalPriceForWheel);
		Utility.verifyTotalPrice(basePriceIntCayman2, priceForEquipmentWheelInt, priceForDeliveryInt, totalPriceForWheelInt);
		
		//select seats
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
		driver.findElement(By.xpath("//*[@id=\"submenu_interieur_x_AI_submenu_x_submenu_parent\"]/span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"submenu_interieur_x_AI_submenu_x_submenu_seats\"]/a")).click();
		 Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"s_interieur_x_PP06\"]")).click();
		
		String priceForSeat = driver.findElement(By.xpath("//*[@id=\"seats_73\"]/div[2]/div[1]/div[3]/div")).getText();
		int priceForSeatInt = Utility.stringToNumber(priceForSeat);
		
		String priceForEquipmentSeat = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int priceForEquipmentSeatInt = Utility.stringToNumber(priceForEquipmentSeat);
		int sumOfPrice2 = sumOfPrice + priceForSeatInt;
		Utility.priceEquality(priceForEquipmentSeatInt,sumOfPrice2 );
		
		String totalPriceWithSeat =driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int totalPriceWithSeatInt = Utility.stringToNumber(totalPriceWithSeat);
		Utility.verifyTotalPrice(basePriceIntCayman2, priceForEquipmentSeatInt, priceForDeliveryInt, totalPriceWithSeatInt);
		
		//select interior carbon fiber
		
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
		driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_submenu_parent\"]/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_IIC\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH_x_c01_PEKH\"]")).click();
		
		String priceForInterior = driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div")).getText();
		int priceForInteriorInt = Utility.stringToNumber(priceForInterior);
	
		String priceForEquipmentInterior = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int priceForEquipmentInteriorInt = Utility.stringToNumber(priceForEquipmentInterior);
		
		int sumOfPrice3 = sumOfPrice2 + priceForInteriorInt;
		Utility.priceEquality(priceForEquipmentInteriorInt, sumOfPrice3);
		
		String totalPriceWithInterior = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int totalPriceWithInteriorInt = Utility.stringToNumber(totalPriceWithInterior);
		Utility.verifyTotalPrice(basePriceIntCayman2, priceForEquipmentInteriorInt, priceForDeliveryInt,totalPriceWithInteriorInt);
		
		//click on performance
		driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_IMG\"]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250_x_c11_M250\"]")).click();
		
		String priceForDop = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250\"]/div[1]/div[2]/div")).getText();
		int priceForDopInt = Utility.stringToNumber(priceForDop);
		
		//scroll down 
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		
		driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M450_x_c91_M450\"]")).click();
		String priceForCeramic = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M450\"]/div[1]/div[2]/div")).getText();
		int priceForCeramicInt = Utility.stringToNumber(priceForCeramic);
		
		String priceForEquipmentLast = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int priceForEquipmentLastInt = Utility.stringToNumber(priceForEquipmentLast);
		
		int sumOfPriceLast = sumOfPrice3 + priceForDopInt + priceForCeramicInt;
		Utility.priceEquality(sumOfPriceLast, priceForEquipmentLastInt);
		
		String totalPriceLast = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int totalPriceLastInt = Utility.stringToNumber(totalPriceLast);
		int s = Utility.verifyTotalPrice(basePriceIntCayman2, priceForEquipmentLastInt, priceForDeliveryInt,totalPriceLastInt);
		System.out.println(s);
		

	}

}

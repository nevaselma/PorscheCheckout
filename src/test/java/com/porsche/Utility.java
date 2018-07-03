package com.porsche;

import org.apache.commons.lang3.math.NumberUtils;

public class Utility {
	
	public static int stringToNumber(String s) {
		StringBuilder s2 = new StringBuilder();
		char[] arr = s.toCharArray();
		for(int i = 0; i<arr.length; i++) {
			if(arr[i]=='.') {
				break;
			}
			if (NumberUtils.isDigits(arr[i]+"")) {
				s2.append(arr[i]);
			}
		}
		int num1 = Integer.parseInt(s2.toString());
		return num1;
		
}
	public static int priceEquality(int actualPrice, int expectedPrice) {
		if(actualPrice == expectedPrice) {
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
			System.out.println("expected: "+ expectedPrice );
			System.out.println("actual: "+ actualPrice);
		}
		
		
		return expectedPrice;
		
	}
	
	public static int verifyTotalPrice(int basePrice, int priceForEquipment, int priceForDelivery, int totalPrice ) {
	if(basePrice + priceForEquipment+ priceForDelivery == totalPrice) {
		System.out.println("Pass " + totalPrice);
	}else {
		System.out.println("Fail");
		System.out.println("expected: "+ basePrice + priceForEquipment+ priceForDelivery );
		System.out.println("actual: "+ totalPrice);
	}
	return totalPrice;
}
	
}

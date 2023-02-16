package com.example.demo;

public class BankService {

	
	public float calcintrest(float pamt, float rate , float time) {
		System.out.println("BankService.calcintrest()");
		
		if(pamt<=0 || rate<=0 || time<=0) {
			throw new IllegalArgumentException(" Invalid inputs ");
			}
		
		try{
			Thread.sleep(1000);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return pamt*rate *time/100.0f;
	}
}

package br.com.m2msolutions.controleviagens.client;

import com.extjs.gxt.ui.client.data.BaseModel;

public class TripBaseModel extends BaseModel {
	
	public static final String TRIP1 = "trip1";
	public static final String TRIP2 = "trip2";
	public static final String TRIP3 = "trip3";
	public static final String TRIP4 = "trip4";
	
	public DTOTrip getTrip1(){
		return get(TRIP1);
	}
	public void setTrip1(DTOTrip trip){
		set(TRIP1,trip);
	}
	public DTOTrip getTrip2(){
		return get(TRIP2);
	}
	public void setTrip2(DTOTrip trip){
		set(TRIP2,trip);
	}
	public DTOTrip getTrip3(){
		return get(TRIP3);
	}
	public void setTrip3(DTOTrip trip){
		set(TRIP3,trip);
	}
	public DTOTrip getTrip4(){
		return get(TRIP4);
	}
	public void setTrip4(DTOTrip trip){
		set(TRIP4,trip);
	}
	
}

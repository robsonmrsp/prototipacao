package br.com.m2msolutions.controleviagens.client;


import java.io.Serializable;

public class DTOArrivedControl implements Serializable {

	private static final long serialVersionUID = 1L;

	private int order;
	
	private String plannedA;
	private String realizedA;
	private String vehicleA;
	private String tableA;

	private String plannedB;
	private String realizedB;
	private String vehicleB;
	private String tableB;
	

	public DTOArrivedControl(String plannedA, String realizedA, String vehicleA, String tableA, String plannedB, String realizedB, String vehicleB, String tableB) {
		super();
		this.plannedA = plannedA;
		this.realizedA = realizedA;
		this.vehicleA = vehicleA;
		this.tableA = tableA;
		this.plannedB = plannedB;
		this.realizedB = realizedB;
		this.vehicleB = vehicleB;
		this.tableB = tableB;
	}

	public String getTableA() {
		return tableA;
	}

	public void setTableA(String tableA) {
		this.tableA = tableA;
	}

	public String getVehicleA() {
		return vehicleA;
	}

	public void setVehicleA(String vehicleA) {
		this.vehicleA = vehicleA;
	}

	public String getRealizedA() {
		return realizedA;
	}

	public void setRealizedA(String realizedA) {
		this.realizedA = realizedA;
	}

	public String getPlannedA() {
		return plannedA;
	}

	public void setPlannedA(String plannedA) {
		this.plannedA = plannedA;
	}

	public String getVehicleB() {
		return vehicleB;
	}

	public void setVehicleB(String vehicleB) {
		this.vehicleB = vehicleB;
	}

	public String getRealizedB() {
		return realizedB;
	}

	public void setRealizedB(String realizedB) {
		this.realizedB = realizedB;
	}

	public String getPlannedB() {
		return plannedB;
	}

	public void setPlannedB(String plannedB) {
		this.plannedB = plannedB;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getTableB() {
		return tableB;
	}

	public void setTableB(String tableB) {
		this.tableB = tableB;
	}
}

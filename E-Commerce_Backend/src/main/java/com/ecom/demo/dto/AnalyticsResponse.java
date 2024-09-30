package com.ecom.demo.dto;

//import lombok.AllArgsConstructor;
//import lombok.Data;

//@Data
//@AllArgsConstructor
public class AnalyticsResponse 
{
	private int placed;
	private int shipped;
	private int delivered;
	private int previousMonthOrders;
	private int currentMonthOrders;
	private double previousMonthEarnings;
	private double currentMonthEarnings;
	
	public AnalyticsResponse(int placed, int shipped, int delivered, int previousMonthOrders, int currentMonthOrders,
					double previousMonthEarnings, double currentMonthEarnings) 
	{
		super();
		this.placed = placed;
		this.shipped = shipped;
		this.delivered = delivered;
		this.previousMonthOrders = previousMonthOrders;
		this.currentMonthOrders = currentMonthOrders;
		this.previousMonthEarnings = previousMonthEarnings;
		this.currentMonthEarnings = currentMonthEarnings;
	}

	public int getPlaced() {
		return placed;
	}

	public void setPlaced(int placed) {
		this.placed = placed;
	}

	public int getShipped() {
		return shipped;
	}

	public void setShipped(int shipped) {
		this.shipped = shipped;
	}

	public int getDelivered() {
		return delivered;
	}

	public void setDelivered(int delivered) {
		this.delivered = delivered;
	}

	public int getPreviousMonthOrders() {
		return previousMonthOrders;
	}

	public void setPreviousMonthOrders(int previousMonthOrders) {
		this.previousMonthOrders = previousMonthOrders;
	}

	public int getCurrentMonthOrders() {
		return currentMonthOrders;
	}

	public void setCurrentMonthOrders(int currentMonthOrders) {
		this.currentMonthOrders = currentMonthOrders;
	}

	public double getPreviousMonthEarnings() {
		return previousMonthEarnings;
	}

	public void setPreviousMonthEarnings(double previousMonthEarnings) {
		this.previousMonthEarnings = previousMonthEarnings;
	}

	public double getCurrentMonthEarnings() {
		return currentMonthEarnings;
	}

	public void setCurrentMonthEarnings(double currentMonthEarnings) {
		this.currentMonthEarnings = currentMonthEarnings;
	}
	
	
}

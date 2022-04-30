package br.inatel.dm112.model;

import java.util.Date;

public class RegisterResponse {

	public enum REGISTER_STATUS {
		NULL_VALUES, ORDER_NOT_FOUND, WRONG_ORDER_STATUS, ORDER_ERROR, OK
	}
	
	private int orderNumber;
	private Date orderDate;
	private Date orderHour;
	private int orderStatus;
	
	public RegisterResponse(int orderNumber, int orderStatus) {
		this.orderNumber = orderNumber;		
		this.orderStatus = orderStatus;
	}
	
	public static RegisterResponse createErrorStatus(int orderNumber, REGISTER_STATUS errorStatus) {
		return new RegisterResponse(orderNumber, errorStatus.ordinal());
	}
	
	public int getStatus() {
		return orderStatus;
	}

	public void setStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	@Override
	public String toString() {
		return "DeliveryStatus [orderNumber=" + orderNumber + ", status=" + orderStatus + ",  orderDate=" 
				+ orderDate + ", orderHour =" + orderHour + "]";
	}
}
	


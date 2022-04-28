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
	
	public RegisterResponse() {
	}
	
	public RegisterResponse(int orderNumber, Date orderDate, Date orderHour, int orderStatus) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderHour = orderHour;
		this.orderStatus = orderStatus;
	}
	
	public static RegisterResponse createErrorStatus(int orderNumber, Date orderDate, Date orderHour, REGISTER_STATUS errorStatus) {
		return new RegisterResponse(orderNumber, orderDate, orderHour, errorStatus.ordinal());
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
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Date getOrderHour() {
		return orderHour;
	}

	public void setOrderHour(Date orderHour) {
		this.orderHour = orderHour;
	}

	@Override
	public String toString() {
		return "DeliveryStatus [orderNumber=" + orderNumber + ", status=" + orderStatus + ",  orderDate=" 
				+ orderDate + ", orderHour =" + orderHour + "]";
	}
}
	


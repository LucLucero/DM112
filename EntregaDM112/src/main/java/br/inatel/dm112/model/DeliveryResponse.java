package br.inatel.dm112.model;


public class DeliveryResponse {

	public enum DELIVERY_STATUS {
		NULL_VALUES, ORDER_NOT_FOUND, WRONG_ORDER_STATUS, ORDER_ERROR, OK
	}
	
	private int orderNumber;
	private int orderStatus;
	
	public DeliveryResponse() {
	}
	
	public DeliveryResponse(int orderNumber, int orderStatus) {
		super();
		this.orderStatus = orderStatus;
		this.orderNumber = orderNumber;
	}
	
	public static DeliveryResponse createErrorStatus(int orderNumber, DELIVERY_STATUS errorStatus) {
		return new DeliveryResponse(orderNumber, errorStatus.ordinal());
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
		return "DeliveryStatus [orderNumber=" + orderNumber + ", status=" + orderStatus + "]";
	}
}


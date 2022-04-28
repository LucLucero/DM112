package br.inatel.dm112.model;

import java.util.Date;

public class Order {

//	@JsonIgnore
	public static enum STATUS {ON_THE_WAY, DELIVERERED}

	private int number;

	private String cpf;

	private int orderStatus;

//	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date orderDate;
	
	private Date orderHour;
	
	public Order() {
	}

	public Order(int number, String cpf, int orderStatus, Date orderDate, Date orderHour) {
		super();
		this.number = number;
		this.cpf = cpf;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderHour = orderHour;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStatus() {
		return orderStatus;
	}

	public void setStatus(int orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	@Override
	public String toString() {
		return "Order [cpf=" + cpf + ", number=" + number + ", status=" + orderStatus + ", orderDate="
				+ orderDate + ", orderHour=" + orderHour + "]";
	}



}

package br.inatel.dm112.model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Orders")
public class Order implements Serializable{

	private static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	

	private String cpf;

	private int orderStatus;
	
	private Date orderDate;
	
	private Date orderHour;

	private int orderNumber;
	

	public static enum STATUS {ON_THE_WAY, DELIVERED}
	
	public Order() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return orderNumber;
	}

	public void setNumber(int number) {
		this.orderNumber = number;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int retrieveOrder(int number) {
		this.orderNumber = getNumber();
		return this.orderNumber;
	}

	public void registerDelivery(Order clientDelivery) {
		clientDelivery.setOrderStatus(1);				
		
	}

	


	
	}





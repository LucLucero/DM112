package br.inatel.dm112.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.inatel.dm112.model.Order.STATUS;

@Entity
@Table(name = "Pedido")
public class OrderEntity {
	
	@Id
	@Column(name = "numero")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer number;

	private String CPF;

	@Column(name = "valor")
	private float value;

	private int orderStatus;

	@Column(name = "dataPedido", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@Column(name = "horaPedido", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date orderHour;

	public OrderEntity() {
		this.orderStatus = STATUS.ON_THE_WAY.ordinal();
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
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

	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String cpf) {
		CPF = cpf;
	}

	@Override
	public String toString() {
		return "OrderEntity [CPF=" + CPF + ", number=" + number + ", status=" + orderStatus + ", orderDate="
				+ orderDate + ", orderHour=" + orderHour + "]";
	}

}
